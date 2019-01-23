package com.codebase.common.config;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public interface StateChangeListener {

    enum State {
        /**
         * 重连
         */
        CONNECTION_RECONNECT,
        /**
         * 断开连接
         */
        CONNECTION_DISCONNECT,
        /**
         * 节点数据变更
         */
        NODE_DATA_CHANGED,
        /**
         * 更新子节点
         */
        CHILD_UPDATED,
        /**
         * 新增子节点
         */
        CHILD_ADDED,
        /**
         * 删除子节点
         */
        CHILD_DELETED;
    }

    /**
     * 状态变更通知
     *
     * @param state
     * @param path
     */
    void onChange(State state, String path);

}
