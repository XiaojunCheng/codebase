package com.codebase.framework.spring.aop.introduction.proxy;

import com.codebase.framework.spring.aop.introduction.Action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Action.before();
        Object result = method.invoke(target, args);
        Action.after();
        return result;
    }
}
