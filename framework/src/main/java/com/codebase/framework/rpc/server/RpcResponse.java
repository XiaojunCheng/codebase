package com.codebase.framework.rpc.server;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
@Data
public class RpcResponse {

    private String requestId;
    private Throwable error;
    private Object result;

}
