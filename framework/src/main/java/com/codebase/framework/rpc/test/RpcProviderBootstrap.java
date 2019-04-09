package com.codebase.framework.rpc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class RpcProviderBootstrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("rpc/provider.xml");
        System.out.println("========");
        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

}
