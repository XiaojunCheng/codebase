package com.codebase.framework.spring.aop.advanced;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PointcutTest {

    @Test
    public void test() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"spring/aop/pointcut.xml"});
        CustomerService service = (CustomerService) appContext.getBean("customerService");

        System.out.println("=== getUserName");
        service.getUserName();

        System.out.println("=== printUrl");
        service.printUrl();

        System.out.println("=== printThrowException");
        try {
            service.printThrowException();
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "error");
        }
    }

}
