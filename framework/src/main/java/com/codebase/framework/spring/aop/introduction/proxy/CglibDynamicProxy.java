package com.codebase.framework.spring.aop.introduction.proxy;

import com.codebase.framework.spring.aop.introduction.Action;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 *
 * NOTE: 直接使用cglib库会报错,使用spring的cglib库就没问题
 */
public class CglibDynamicProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Action.before();
        Object result = methodProxy.invokeSuper(o, objects);
        Action.after();
        return result;
    }
}
