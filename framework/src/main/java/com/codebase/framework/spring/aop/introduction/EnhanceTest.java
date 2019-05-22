package com.codebase.framework.spring.aop.introduction;

import com.codebase.framework.spring.aop.introduction.advice.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/13
 */
public class EnhanceTest {

    @Test
    public void testBeforeAndAfterAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();     //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());         //射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); //添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());  //添加后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy(); //从代理工厂中获取代理
        greeting.sayHello("Jack");                              //调用代理的方法
    }

    @Test
    public void testBeforeAndAfterAdvice2() {
        ProxyFactory proxyFactory = new ProxyFactory();     //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());         //射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice()); //添加前置及后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy(); //从代理工厂中获取代理
        greeting.sayHello("Jack");                              //调用代理的方法
    }

    @Test
    public void testAroundAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();     //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());         //射入目标类对象
        proxyFactory.addAdvice(new GreetingAroundAdvice()); //添加环绕增强

        Greeting greeting = (Greeting) proxyFactory.getProxy(); //从代理工厂中获取代理
        greeting.sayHello("Jack");                              //调用代理的方法
    }

    @Test
    public void testThrowsAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();     //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());         //射入目标类对象
        proxyFactory.addAdvice(new GreetingThrowsAdvice()); //添加环绕增强

        Greeting greeting = (Greeting) proxyFactory.getProxy(); //从代理工厂中获取代理
        try {
            greeting.throwException();
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(), "throw");
        }
    }

    @Test
    public void testAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/spring/enhance.xml"); // 获取 Spring Context
        Greeting greeting = (Greeting) context.getBean("greetingProxy");                        // 从 Context 中根据 id 获取 Bean 对象（其实就是一个代理）
        greeting.sayHello("Jack");
    }

}
