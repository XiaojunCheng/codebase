package com.codebase.framework.rpc.provider;

import com.codebase.framework.rpc.HelloService;
import com.codebase.framework.rpc.server.RpcService;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }
}
