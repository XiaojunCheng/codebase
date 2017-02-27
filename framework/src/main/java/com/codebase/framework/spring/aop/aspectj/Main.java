package com.codebase.framework.spring.aop.aspectj;

import com.codebase.framework.spring.aop.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"spring/aspectj.xml"});

        CustomerService service = (CustomerService) appContext.getBean("customerService");

        System.out.println("===========================");
        service.printName();
        System.out.println("===========================");
        service.printUrl();
        System.out.println("===========================");
        try {
            service.printThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===========================");
        service.printAround("hello");

    }

}
