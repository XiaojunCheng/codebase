package com.codebase.framework.spring.reconstruct.context;

import com.codebase.framework.spring.reconstruct.bean.step2.AbstractBeanDefinitionReader;
import com.codebase.framework.spring.reconstruct.bean.step2.factory.AutowireCapableBeanFactory;
import com.codebase.framework.spring.reconstruct.bean.step2.xml.ClassPathXmlResourceLoader;
import com.codebase.framework.spring.reconstruct.bean.step2.xml.XmlBeanDefinitionReader;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private final AbstractBeanDefinitionReader beanDefinitionReader;
    private final String[] resolvedLocations;

    public ClassPathXmlApplicationContext(String... locations) {
        super(new AutowireCapableBeanFactory());
        this.beanDefinitionReader = new XmlBeanDefinitionReader(new ClassPathXmlResourceLoader());
        this.resolvedLocations = new String[locations.length];
        for (int index = 0; index < resolvedLocations.length; index++) {
            resolvedLocations[index] = resolvedLocation(locations[index]);
        }

        refresh();
    }

    private String resolvedLocation(String location) {
        return location;
    }

    @Override
    protected void refresh() {
        beanDefinitionReader.loadBeanDefinitions(resolvedLocations);
        beanDefinitionReader.getBeanDefinitions().forEach(beanFactory::registerBeanDefinition);
    }
}
