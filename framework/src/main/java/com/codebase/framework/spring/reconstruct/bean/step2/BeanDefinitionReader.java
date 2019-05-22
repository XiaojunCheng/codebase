package com.codebase.framework.spring.reconstruct.bean.step2;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public interface BeanDefinitionReader {

    int loadBeanDefinitions(String... configLocations);

}
