package com.codebase.fundation.io.mmap;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mmap文件引用计数
 * 1. shutdown方法要保证只能由资源管理线程调用来释放资源
 * 2. release方法只能由非资源管理线程调用
 */
public abstract class AbstractReferenceCounted {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReferenceCounted.class);

    private AtomicBoolean shouldRun = new AtomicBoolean(true);

    // 引用计数，主线程引用为1
    protected AtomicLong refCount = new AtomicLong(1);

    // 资源是否已经被清理
    protected volatile boolean cleanupOver = false;

    protected final Object notifyObj = new Object();

    /**
     * 获取资源锁，由非资源管理线程调用
     */
    public synchronized boolean retain() {

        if (!shouldRun.get()) {
            return false;
        }

        if (refCount.get() > 0) {
            refCount.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 释放资源锁，由非资源管理线程调用
     */
    public synchronized void release() {
        refCount.decrementAndGet();
    }

    public synchronized boolean close() {
        return close(false);
    }

    /**
     * 尝试关闭资源，只能由管理线程调用
     * 
     * @param force 是否强制关闭
     */
    public synchronized boolean close(boolean force) {

        if (shouldRun.compareAndSet(true, false)) {
            refCount.decrementAndGet();
        }

        if (refCount.get() <= 0) {
            if (!cleanupOver) { // 只有当没有被释放且没有被关闭时才清除资源
                this.cleanupOver = this.cleanup();
            }
        }

        if (!cleanupOver & force) { // 强制清除
            this.cleanupOver = this.cleanup();
        }

        return isCleanupOver();
    }

    public long getRefCount() {
        return refCount.get();
    }

    public abstract boolean cleanup();

    // 资源是否被清理完成
    public boolean isCleanupOver() {
        LOGGER.info("current ref {}", getRefCount());
        return this.cleanupOver;
    }
}
