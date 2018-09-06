package com.codebase.framework.aop.spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Xiaojun.Cheng
 * @date 2017/10/1
 */
public class Main {

    public static void main(String[] args) {

        //JDK Proxy方式
        Camera camera = (Camera) Proxy.newProxyInstance(Camera.class.getClassLoader(),
                new Class[]{Camera.class},
                new JDKInvocationHandler(new Camera() {
                    @Override
                    public void play() {
                        System.out.println("mini");
                    }
                }));
        camera.play();

        //Cglib
        TV tv = (TV) Enhancer.create(TV.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.out.println("Cglib method before: " + method.getName());
                try {
                    return proxy.invokeSuper(obj, args);
                } finally {
                    System.out.println("Cglib method after: " + method.getName());
                }
            }
        });
        tv.play();

        Runnable run = (Runnable) Proxy.newProxyInstance(Runnable.class.getClassLoader(),
                new Class[]{Runnable.class},
                new JDKInvocationHandler(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("hello");
                    }
                }));
        run.run();
    }

    static class JDKInvocationHandler implements InvocationHandler {

        private final Object target;

        public JDKInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("JDK method before: " + method.getName());
            try {
                return method.invoke(target, args);
            } finally {
                System.out.println("JDK method after: " + method.getName());
            }
        }
    }

}
