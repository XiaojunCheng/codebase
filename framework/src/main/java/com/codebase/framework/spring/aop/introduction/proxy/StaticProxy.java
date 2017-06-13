package com.codebase.framework.spring.aop.introduction.proxy;

import com.codebase.framework.spring.aop.introduction.Action;
import com.codebase.framework.spring.aop.introduction.Greeting;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
public class StaticProxy implements Greeting {

    private Greeting greeting;

    public StaticProxy(Greeting greeting) {
        this.greeting = greeting;
    }

    @Override
    public void sayHello(String name) {
        Action.before();
        greeting.sayHello(name);
        Action.after();
    }

    @Override
    public void throwException() {

    }

}
