package com.codebase.framework.spring.aop.advanced.advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
public class ThrowExceptionAdvice implements ThrowsAdvice {

    public void afterThrowing(IllegalArgumentException e) throws Throwable {
        System.out.println("ThrowExceptionAdvice: " + e.getMessage());
    }

}
