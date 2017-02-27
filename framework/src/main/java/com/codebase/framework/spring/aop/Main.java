package com.codebase.framework.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"spring/aop-pointcut.xml"});

        CustomerService service = (CustomerService) appContext.getBean("customerServiceProxy");

        System.out.println("*************************");
        service.printName();
        System.out.println("*************************");
        service.printUrl();
        System.out.println("*************************");
        try {
            service.printThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
