package com.codebase.framework.spring.reconstruct.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 被代理的对象
 */
@AllArgsConstructor
@Getter
public class TargetSource {

    private final Object target;

    private final Class targetClass;
}
