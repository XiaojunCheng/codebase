package com.codebase.framework.spring.reconstruct.aop;

import com.codebase.framework.spring.reconstruct.SayHelloService;
import com.codebase.framework.spring.reconstruct.SayHelloServiceImpl;

public class AspectJExpressionPointcutTest {

    public static void main(String[] args) throws Exception {
        testClassFilter();
        testMethodInterceptor();
    }

    public static void testClassFilter() throws Exception {
        String expression = "execution(* com.codebase.framework.spring.reconstruct.aop.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(SayHelloService.class);
        System.out.println(matches);
    }

    public static void testMethodInterceptor() throws Exception {
        String expression = "execution(* com.codebase.framework.spring.reconstruct.aop.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(SayHelloServiceImpl.class.getDeclaredMethod("sayHello"), SayHelloServiceImpl.class);
        System.out.println(matches);
    }
}
