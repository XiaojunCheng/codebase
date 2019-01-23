package com.codebase.framework.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/28
 */
public class HelloTest {

    public static void main(String[] args) {
        final String name = "boom";

        Hello hello = new HelloImpl();
        hello.sayHello(name);

        Hello staticProxyHello = new StaticProxiedHello();
        staticProxyHello.sayHello(name);

        // 2. 然后在需要使用Hello的时候，通过JDK动态代理获取Hello的代理对象。
        Hello dynamicProxyHello = (Hello) Proxy.newProxyInstance(
                //类加载器
                HelloTest.class.getClassLoader(),
                //代理需要实现的接口，可以有多个
                new Class<?>[]{Hello.class},
                //方法调用的实际处理者
                new DynamicProxyInvocationHandler(new HelloImpl()));
        dynamicProxyHello.sayHello(name);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        Hello cglibDynamicProxyHello = (Hello) enhancer.create();
        cglibDynamicProxyHello.sayHello(name);
    }

}


