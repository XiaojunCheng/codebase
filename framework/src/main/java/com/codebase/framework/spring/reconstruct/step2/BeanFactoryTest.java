package com.codebase.framework.spring.reconstruct.step2;

import com.codebase.framework.spring.reconstruct.SayHelloService;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class BeanFactoryTest {

    public static void main(String[] args) {

        //1. 初始化bean工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //2. 初始化bean
        BeanDefinition definition = new BeanDefinition();
        definition.setName("sayHelloService");
        definition.setClassName("com.codebase.framework.spring.reconstruct.SayHelloServiceImpl");

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.add(new PropertyValue("name", "anybody"));
        definition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition(definition);

        //3. 获取bean
        SayHelloService service = (SayHelloService) beanFactory.getBean("sayHelloService");
        service.sayHello();
    }

}
