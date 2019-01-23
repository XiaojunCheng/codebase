package com.codebase.common.config;

import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public interface ConfigClient {

    /**
     * 添加连接状态监听器
     *
     * @param listener
     */
    void addStateChangeListener(StateChangeListener listener);

    /**
     * 检查节点是否存在
     *
     * @param path
     * @return
     * @throws ConfigException
     */
    boolean checkPathExists(String path) throws ConfigException;

    /**
     * 创建一个节点并包含数据,在失去连接后不会删除.
     * <p/>
     * save是持久化存储,如果是临时数据,请使用register
     *
     * @param path
     * @param data
     * @param <T>
     * @throws ConfigException
     */
    <T> void save(String path, T data) throws ConfigException;

    /**
     * 注册一个数据,在连接断开时需要重新删除,重连后重新注册
     *
     * @param path
     * @param data
     * @param <T>
     * @throws ConfigException
     */
    <T> void register(String path, T data) throws ConfigException;

    /**
     * 获取数据
     *
     * @param path
     * @param clazz
     * @param <T>
     * @return
     * @throws ConfigException
     */
    <T> T get(String path, Class<T> clazz) throws ConfigException;

    /**
     * 删除数据,如果有子节点也会删除
     *
     * @param path
     * @throws ConfigException
     */
    void delete(String path) throws ConfigException;

    /**
     * 监听数据变化
     *
     * @param path
     * @param listener
     */
    void watch(String path, StateChangeListener listener) throws ConfigException;

    /**
     * 获取路径下的子节点
     *
     * @param path
     * @return
     * @throws ConfigException
     */
    List<String> getChildren(String path) throws ConfigException;

    /**
     * 监听子节点的变化并通知出来
     *
     * @param path
     * @param listener
     * @return
     * @throws ConfigException
     */
    void watchChildren(String path, StateChangeListener listener) throws ConfigException;

    /**
     * 取消监听子节点的变化
     *
     * @param path
     * @return
     */
    void cancelWatchChildren(String path);

    /**
     * 锁住某个节点
     *
     * @param path
     * @param timeoutMS
     * @param data
     * @param <T>
     * @return
     * @throws ConfigException
     */
    <T> void lock(String path, long timeoutMS, T data) throws ConfigException;

    /**
     * 释放节点锁
     *
     * @param path
     */
    void unLock(String path);

    /**
     * 资源释放
     */
    void close();

}
