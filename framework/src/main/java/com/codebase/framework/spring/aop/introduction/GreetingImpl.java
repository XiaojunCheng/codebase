package com.codebase.framework.spring.aop.introduction;

import org.springframework.stereotype.Component;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
@Component
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("hello, " + name + "!");
    }

    @Override
    public void throwException() {
        throw new RuntimeException("throw");
    }
}
