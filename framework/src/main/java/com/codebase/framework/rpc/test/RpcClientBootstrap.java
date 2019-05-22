package com.codebase.framework.rpc.test;

import com.codebase.framework.rpc.HelloService;
import com.codebase.framework.rpc.client.RpcProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcClientBootstrap {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("rpc/client.xml");
        RpcProxy rpcProxy = ctx.getBean(RpcProxy.class);
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.sayHello("World");
        System.out.println(result);
    }

}
