package com.codebase.framework.spring.aop.introduction.advice;


import com.codebase.framework.spring.aop.introduction.Action;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
@Component
public class GreetingAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Action.before();
        Object result = invocation.proceed();
        Action.after();
        return result;
    }
}
