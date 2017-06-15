package com.codebase.framework.spring.aop.advanced.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.codebase.framework.spring.aop.advanced.CustomerService.printUrl(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore: " + joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.codebase.framework.spring.aop.advanced.CustomerService.printUrl(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter: " + joinPoint.getSignature().getName());
    }

    @AfterReturning
            (pointcut = "execution(* com.codebase.framework.spring.aop.advanced.CustomerService.getUserName(..))",
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("logAfterReturning: " + joinPoint.getSignature().getName() + ", returned value: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.codebase.framework.spring.aop.advanced.CustomerService.printThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("logAfterThrowing: " + joinPoint.getSignature().getName() + ", exception: " + error);
    }

    @Around("execution(* com.codebase.framework.spring.aop.advanced.CustomerService.printAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround method: " + joinPoint.getSignature().getName() + ", arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("logAround before");
        joinPoint.proceed();
        System.out.println("logAround after");
    }

}
