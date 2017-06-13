package com.codebase.framework.spring.reconstruct.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long time = System.nanoTime();
        System.out.println("Invocation of Method " + methodName + " start!");
        Object proceed = invocation.proceed();
        System.out.println("Invocation of Method " + methodName + " end! takes " + (System.nanoTime() - time) + " nanoseconds.");
        return proceed;
    }
}
