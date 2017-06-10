package com.codebase.framework.spring.reconstruct.bean.step2;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
@Data
public class BeanDefinition {

    private String name;

    private String className;

    private Class beanClass;

    private Object bean;

    private PropertyValues propertyValues;

}
