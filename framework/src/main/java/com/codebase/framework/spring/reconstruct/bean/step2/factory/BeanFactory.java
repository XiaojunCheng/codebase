package com.codebase.framework.spring.reconstruct.bean.step2.factory;

import com.codebase.framework.spring.reconstruct.bean.step2.BeanDefinition;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(BeanDefinition beanDefinition);

}
