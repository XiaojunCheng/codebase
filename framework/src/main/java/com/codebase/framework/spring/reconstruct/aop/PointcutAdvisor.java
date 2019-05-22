package com.codebase.framework.spring.reconstruct.aop;



public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
