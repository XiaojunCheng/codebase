package com.codebase.framework.spring.reconstruct.step1;

import com.codebase.framework.spring.reconstruct.SayHelloService;
import com.codebase.framework.spring.reconstruct.SayHelloServiceImpl;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class BeanFactoryTest {

    public static void main(String[] args) {

        //1. 初始化bean工厂
        BeanFactory beanFactory = new BeanFactory();

        //2. 初始化bean
        BeanDefinition definition = new BeanDefinition(new SayHelloServiceImpl());
        beanFactory.registerBeanDefinition("sayHelloService", definition);

        //3. 获取bean
        SayHelloService service = (SayHelloService) beanFactory.getBean("sayHelloService");
        service.sayHello();
    }

}
