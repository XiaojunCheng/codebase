package com.codebase.framework.spring.reconstruct.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于jdk的动态代理
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{advised.getTargetSource().getTargetClass()},
                this);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        MethodInterceptor interceptor = advised.getMethodInterceptor();
        MethodInvocation invocation = new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args);
        return interceptor.invoke(invocation);
    }

}
