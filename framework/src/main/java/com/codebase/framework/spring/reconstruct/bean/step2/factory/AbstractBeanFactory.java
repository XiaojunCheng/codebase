package com.codebase.framework.spring.reconstruct.bean.step2.factory;

import com.codebase.framework.spring.reconstruct.bean.step2.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name) {
        if (!beanDefinitionMap.containsKey(name)) {
            return null;
        }

        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
