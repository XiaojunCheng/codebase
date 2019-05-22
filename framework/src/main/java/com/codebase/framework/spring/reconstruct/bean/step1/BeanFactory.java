package com.codebase.framework.spring.reconstruct.bean.step1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name) {
        if (!beanDefinitionMap.containsKey(name)) {
            return null;
        }

        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
