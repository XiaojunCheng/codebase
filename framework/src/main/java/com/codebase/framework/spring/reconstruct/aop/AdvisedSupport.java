package com.codebase.framework.spring.reconstruct.aop;

import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关的元数据
 */
@Getter
@Setter
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;
}
