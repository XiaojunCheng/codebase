package com.codebase.framework.curator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.codebase.common.util.ByteUtil;

public class ZkClientImpl implements ZkClient, ConnectionStateListener {

    private static final Logger logger = LoggerFactory.getLogger(ZkClientImpl.class);

    private final CuratorFramework curator;

    //监听连接状态
    private Set<ConnectionStateListener> connectionWatchers = new HashSet<>();

    //register path需要在重连时重建路径
    private Map<String, PathNode> registers = new ConcurrentHashMap<>();

    //监听节点数据变化的缓存
    private final Map<String, NodeCache> dataWatchers = new ConcurrentHashMap<>();

    //监听子节点变化的缓存
    private final Map<String, PathChildrenCache> childrenWatcher = new ConcurrentHashMap<>();

    //所有持有的锁
    private final Map<String, Lock> locks = new ConcurrentHashMap<>();

    private final ReentrantLock watchLock = new ReentrantLock();

    private final ExecutorService executor;

    public ZkClientImpl(String zkAddress) {
        this(zkAddress, Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors(), 16),
                ThreadUtils.newThreadFactory("PathChildrenCache")));
    }

    public ZkClientImpl(String zkAddress, ExecutorService executor) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkAddress);
        builder.retryPolicy(retryPolicy);
        builder.sessionTimeoutMs(12000).connectionTimeoutMs(3000);
        curator = builder.build();
        curator.getConnectionStateListenable().addListener(this);
        curator.start();
        this.executor = executor;
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {

        logger.info("[zk] current connection status is {}", newState);
        notifyConnectionStateChange(client, newState);
        switch (newState) {
            case CONNECTED:
            case RECONNECTED:
                register();
                break;
            default:
                return;
        }
    }

    private void register() {
        for (Map.Entry<String, PathNode> entry : registers.entrySet()) {
            String path = entry.getKey();
            PathNode node = entry.getValue();
            try {
                register(path, node.data);
            } catch (ZkException e) {
                logger.warn("register " + path, e);
            }
        }
    }

    private void notifyConnectionStateChange(CuratorFramework client, ConnectionState newState) {
        for (ConnectionStateListener listener : connectionWatchers) {
            listener.stateChanged(client, newState);
        }
    }

    @Override
    public boolean checkPathExists(String path) throws ZkException {
        try {
            return curator.checkExists().forPath(path) != null;
        } catch (Exception e) {
            String info = String.format("[zk] Failed to check exist for path [%s]", path);
            logger.warn(info);
            return false;
        }
    }

    @Override
    public <T> T get(String path, Class<T> clazz) throws ZkException {
        try {
            byte[] bytes = curator.getData().forPath(path);
            return JSON.parseObject(bytes, clazz);
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    @Override
    public List<String> getChildren(String path) throws ZkException {
        try {
            return curator.getChildren().forPath(path);
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    public <T> void save(String path, T data) throws ZkException {
        try {
            byte[] bytes = ByteUtil.EMPTY;
            if (data != null) {
                bytes = JSON.toJSONBytes(data);
            }
            if (checkPathExists(path)) {
                curator.setData().forPath(path, bytes);
            } else {
                curator.create().creatingParentsIfNeeded().forPath(path, bytes);
            }
        } catch (Exception e) {
            logger.warn("create " + path, e);
            throw new ZkException(e);
        }
    }

    @Override
    public <T> void register(final String path, final T data) throws ZkException {
        watchLock.lock();
        try {
            byte[] bytes = ByteUtil.EMPTY;
            if (data != null) {
                bytes = JSON.toJSONBytes(data);
            }
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, bytes);
            if (!registers.containsKey(path)) {
                registers.put(path, new PathNode(bytes));
            }
        } catch (Exception e) {
            logger.warn("create " + path, e);
            throw new ZkException(e);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void delete(String path) throws ZkException {
        watchLock.lock();
        try {
            registers.remove(path);
            if (checkPathExists(path)) {
                curator.delete().deletingChildrenIfNeeded().forPath(path);
            }
        } catch (Exception e) {
            throw new ZkException(e);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void addConnectionStateListener(ConnectionStateListener listener) {
        watchLock.lock();
        try {
            connectionWatchers.add(listener);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void watch(final String path, final NodeCacheListener listener) throws ZkException {
        watchLock.lock();
        try {
            NodeCache nodeCache = dataWatchers.get(path);
            if (nodeCache == null) {
                nodeCache = new NodeCache(curator, path);
                nodeCache.start();
                dataWatchers.put(path, nodeCache);
            }
            nodeCache.getListenable().addListener(listener);
        } catch (Exception e) {
            throw new ZkException(e);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void watchChildren(final String path, final PathChildrenCacheListener listener) throws ZkException {
        watchLock.lock();
        try {
            PathChildrenCache pathChildrenCache = childrenWatcher.get(path);
            if (pathChildrenCache == null) {
                pathChildrenCache = new PathChildrenCache(curator, path, false, false, executor);
                pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
                childrenWatcher.put(path, pathChildrenCache);
            }
            pathChildrenCache.getListenable().addListener(listener);
        } catch (Exception e) {
            throw new ZkException(e);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void unWatchChildren(String path) {
        watchLock.lock();
        try {
            PathChildrenCache pathChildrenCache = childrenWatcher.remove(path);
            if (pathChildrenCache != null) {
                try {
                    pathChildrenCache.close();
                } catch (IOException e) {
                    logger.warn("close node cache for path {} error", path, e);
                }
            }
        } finally {
            watchLock.unlock();
        }

    }

    @Override
    public <T> void acquireLock(String path, long timeoutMS, T t) throws ZkException {
        watchLock.lock();
        try {
            Lock lock = locks.get(path);
            if (lock != null) {
                if (lock.isAcquiredInThisProcess()) {
                    return;
                }
                lock.release();
                locks.remove(path);
            }
            InterProcessSemaphoreMutex mutex = new InterProcessSemaphoreMutex(curator, path);
            boolean locked = mutex.acquire(timeoutMS, TimeUnit.MILLISECONDS);
            if (!locked) {
                throw new ZkException("lock " + path + " failed " + timeoutMS);
            }
            if (t != null) {
                curator.setData().forPath(path, JSON.toJSONBytes(t));
            }
            lock = new Lock(mutex, path);
            locks.put(path, lock);
        } catch (Exception e) {
            logger.warn("lock " + path, e);
            throw new ZkException(e);
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void releaseLock(String path) {
        watchLock.lock();
        try {
            Lock lock = locks.remove(path);
            if (lock != null) {
                lock.release();
            }
        } finally {
            watchLock.unlock();
        }
    }

    @Override
    public void close() {
        connectionWatchers.clear();
        registers.clear();
        for (Map.Entry<String, NodeCache> entry : dataWatchers.entrySet()) {
            String path = entry.getKey();
            NodeCache nodeCache = entry.getValue();
            try {
                nodeCache.close();
            } catch (Exception e) {
                logger.warn("close node cache error", e);
            }
            dataWatchers.remove(path);
        }
        for (Map.Entry<String, PathChildrenCache> entry : childrenWatcher.entrySet()) {
            String path = entry.getKey();
            PathChildrenCache pathChildrenCache = entry.getValue();
            try {
                pathChildrenCache.close();
            } catch (IOException e) {
                logger.warn("close children cache error", e);
            }
            childrenWatcher.remove(path);
        }
        for (Map.Entry<String, Lock> entry : locks.entrySet()) {
            String path = entry.getKey();
            Lock lock = entry.getValue();
            lock.release();
            locks.remove(path);
        }
        curator.close();
        executor.shutdown();
    }

    private class Lock {
        private final InterProcessSemaphoreMutex mutex;
        private final String path;

        public Lock(InterProcessSemaphoreMutex mutex, String path) {
            this.mutex = mutex;
            this.path = path;
        }

        public void release() {
            locks.remove(path);
            try {
                mutex.release();
            } catch (Exception e) {
                logger.warn("release path {} lock error {}", path, e.getMessage());
            }
        }

        public boolean isAcquiredInThisProcess() {
            return mutex.isAcquiredInThisProcess();
        }
    }

    private class PathNode {
        public final byte[] data;

        public PathNode(byte[] data) {
            this.data = data;
        }
    }
}
