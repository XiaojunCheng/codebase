package com.codebase.framework.curator;

import java.util.List;

import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;

public interface ZkClient {

    /**
     * 检查节点是否存在
     */
    boolean checkPathExists(String path) throws ZkException;

    /**
     * 获取数据
     */
    <T> T get(String path, Class<T> clazz) throws ZkException;

    /**
     * 获取路径下的子节点
     */
    List<String> getChildren(String path) throws ZkException;

    /**
     * 创建一个持久化节点并包含数据，在失去连接后不会删除
     */
    <T> void save(String path, T data) throws ZkException;

    /**
     * 注册一个临时节点并包含数据，在失去连接后节点会被删除，重连时会进行重新创建
     */
    <T> void register(String path, T data) throws ZkException;

    /**
     * 删除数据，如果有子节点也会删除
     */
    void delete(String path) throws ZkException;

    /**
     * 添加连接状态监听器
     */
    void addConnectionStateListener(ConnectionStateListener listener);

    /**
     * 监听节点数据变化
     */
    void watch(String path, NodeCacheListener listener) throws ZkException;

    /**
     * 监听子节点变化并通知出来
     */
    void watchChildren(String path, PathChildrenCacheListener listener) throws ZkException;

    /**
     * 取消监听子节点的变化
     *
     * @param path
     */
    void unWatchChildren(String path);

    /**
     * 获取节点锁
     */
    <T> void acquireLock(String path, long timeoutMS, T data) throws ZkException;

    /**
     * 取消节点锁
     */
    void releaseLock(String path);

    /**
     * 释放所有资源
     */
    void close();
}
