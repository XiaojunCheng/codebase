package com.codebase.framework.rpc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcProviderBootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rpc/provider.xml");
    }

}
