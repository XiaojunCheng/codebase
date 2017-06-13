package com.codebase.framework.spring.aop.advanced.aspectj;

import com.codebase.framework.spring.aop.advanced.CustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectjTest {

    @Test
    public void test() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"spring/aop/aspectj.xml"});
        CustomerService service = (CustomerService) appContext.getBean("customerService");

        System.out.println("======= getUserName");
        service.getUserName();

        System.out.println("======= printUrl");
        service.printUrl();

        try {
            System.out.println("======= printThrowException");
            service.printThrowException();
        } catch (Exception e) {
            //ignore
        }
    }

}
