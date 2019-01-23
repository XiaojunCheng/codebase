package com.codebase.framework.rpc.server;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
@Data
public class RpcRequest {

    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数
     */
    private Object[] parameters;
}
