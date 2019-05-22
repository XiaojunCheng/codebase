package com.codebase.framework.spring.aop.advanced.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("[Method] name: " + methodInvocation.getMethod().getName());
        System.out.println("[Method] arguments: " + Arrays.toString(methodInvocation.getArguments()));
        try {
            //same with MethodBeforeAdvice
            System.out.println("[Method] before");
            Object result = methodInvocation.proceed();
            //same with AfterReturningAdvice
            System.out.println("[Method] after, returning: " + result);
            return result;
        } catch (IllegalArgumentException e) {
            //same with ThrowsAdvice
            System.out.println("[Method] throw exception: " + e);
            throw e;
        }
    }
}
