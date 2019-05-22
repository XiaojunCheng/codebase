package com.codebase.framework.spring.reconstruct.context;

import com.codebase.framework.spring.reconstruct.bean.step2.BeanDefinition;
import com.codebase.framework.spring.reconstruct.bean.step2.factory.BeanFactory;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected final BeanFactory beanFactory;

    public AbstractApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected abstract void refresh();

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }

}
