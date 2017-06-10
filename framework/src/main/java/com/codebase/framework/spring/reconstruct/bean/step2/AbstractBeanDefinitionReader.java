package com.codebase.framework.spring.reconstruct.bean.step2;

import com.codebase.framework.spring.reconstruct.bean.step2.io.ResourceLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
    }

    public List<BeanDefinition> getBeanDefinitions() {
        return new ArrayList<>(beanDefinitionMap.values());
    }

}
