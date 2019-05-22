package com.codebase.framework.spring.aop.advanced.aspectj;

import com.codebase.framework.spring.aop.advanced.MethodActionType;
import com.codebase.framework.spring.aop.advanced.MethodAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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

    @Around("@annotation(com.codebase.framework.spring.aop.advanced.MethodAnnotation)")
    public void logAroundAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = signature.getName();

        Method callMethod = signature.getMethod();
        MethodAnnotation methodAnnotation = callMethod.getAnnotation(MethodAnnotation.class);
        MethodActionType actionType = methodAnnotation.actionType() == null ? MethodActionType.OPERATE : methodAnnotation.actionType();
        System.out.println(actionType);

        System.out.println("logAround method: " + method + ", arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("logAround method: " + joinPoint.getSignature().getName() + ", arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("logAround before");
        joinPoint.proceed();
        System.out.println("logAround after");
    }

}
