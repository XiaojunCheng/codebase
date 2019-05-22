package com.codebase.framework.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/29
 */
public class DynamicProxyInvocationHandler implements InvocationHandler {

    private Hello hello;
    public DynamicProxyInvocationHandler(Hello hello) {
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
            System.out.println("You said: " + Arrays.toString(args));
        }
        return method.invoke(hello, args);
    }
}
