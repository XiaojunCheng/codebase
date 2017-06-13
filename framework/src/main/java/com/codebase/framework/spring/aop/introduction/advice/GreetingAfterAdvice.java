package com.codebase.framework.spring.aop.introduction.advice;

import com.codebase.framework.spring.aop.introduction.Action;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
@Component
public class GreetingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        Action.after();
    }

}
