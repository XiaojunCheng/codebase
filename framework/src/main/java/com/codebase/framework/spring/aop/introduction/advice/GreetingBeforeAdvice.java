package com.codebase.framework.spring.aop.introduction.advice;

import com.codebase.framework.spring.aop.introduction.Action;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
@Component
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        Action.before();
    }

}
