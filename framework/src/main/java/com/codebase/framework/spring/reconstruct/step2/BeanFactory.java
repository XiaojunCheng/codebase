package com.codebase.framework.spring.reconstruct.step2;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(BeanDefinition beanDefinition);

}
