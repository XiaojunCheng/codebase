package com.codebase.framework.dynamicproxy;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/28
 */
public class StaticProxiedHello implements Hello {

    private Hello hello = new HelloImpl();

    @Override
    public void sayHello(String name) {
        System.out.println("You said: " + name);
        hello.sayHello(name);
    }
}
