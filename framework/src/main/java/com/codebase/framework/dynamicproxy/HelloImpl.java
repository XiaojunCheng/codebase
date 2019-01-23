package com.codebase.framework.dynamicproxy;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/28
 */
public class HelloImpl implements Hello {

    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
