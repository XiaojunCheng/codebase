package com.codebase.framework.spring.reconstruct.context;

import com.codebase.framework.spring.reconstruct.SayHelloService;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class ApplicationContextTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/reconstruct/spring.xml");
        SayHelloService service = (SayHelloService) context.getBean("sayHelloService");
        service.sayHello();
    }

}
