package com.codebase.framework.spring.reconstruct.bean.step2;

import com.codebase.framework.spring.reconstruct.SayHelloService;
import com.codebase.framework.spring.reconstruct.bean.step2.factory.AutowireCapableBeanFactory;
import com.codebase.framework.spring.reconstruct.bean.step2.factory.BeanFactory;
import com.codebase.framework.spring.reconstruct.bean.step2.xml.ClassPathXmlResourceLoader;
import com.codebase.framework.spring.reconstruct.bean.step2.xml.XmlBeanDefinitionReader;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class BeanFactoryTest {

    public static void main(String[] args) {

        //1. 加载bean配置
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(new ClassPathXmlResourceLoader());
        beanDefinitionReader.loadBeanDefinitions("aop/reconstruct/spring.xml");

        //2. 初始化bean工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanDefinitionReader.getBeanDefinitions().forEach(beanFactory::registerBeanDefinition);

        //3. 获取bean
        SayHelloService service = (SayHelloService) beanFactory.getBean("sayHelloService");
        service.sayHello();
    }

}
