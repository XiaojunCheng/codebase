package com.codebase.framework.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.codebase.framework.spring.aop.CustomerService.printUrl(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore() is running!");
        System.out.println("Hijacked : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.codebase.framework.spring.aop.CustomerService.printUrl(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter() is running!");
        System.out.println("Hijacked: " + joinPoint.getSignature().getName());
    }

    @AfterReturning
            (pointcut = "execution(* com.codebase.framework.spring.aop.CustomerService.printName(..))",
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("logAfterReturning() is running!");
        System.out.println("Hijacked: " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.codebase.framework.spring.aop.CustomerService.printThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked: " + joinPoint.getSignature().getName());
        System.out.println("Exception: " + error);
    }

    @Around("execution(* com.codebase.framework.spring.aop.CustomerService.printAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method: " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments: " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");
    }

}
