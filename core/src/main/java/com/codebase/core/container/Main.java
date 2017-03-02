package com.codebase.core.container;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        BundleContainer container = new BundleContainer();
        container.start();
        container.stop();
        ILoggerFactory factory = LoggerFactory.getILoggerFactory();
        System.out.println(factory.getClass());
    }

}
