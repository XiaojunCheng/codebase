package com.codebase.framework.spring.aop.aspectj;

import com.codebase.framework.spring.aop.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"spring/aspectj.xml"});

        CustomerService service = (CustomerService) appContext.getBean("customerService");

        LOGGER.info("===========================");
        service.printName();

        LOGGER.info("===========================");

        service.printUrl();
        LOGGER.info("===========================");

        try {
            service.printThrowException();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("===========================");
        service.printAround("hello");
    }

}
