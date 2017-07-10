package com.codebase.framework.spring.aop.advanced;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xiaojun.Cheng
 * @date 2017/7/10
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value= ElementType.METHOD)
public @interface MethodAnnotation {

    MethodActionType actionType() default MethodActionType.OPERATE;

}
