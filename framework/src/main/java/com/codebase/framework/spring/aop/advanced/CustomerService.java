package com.codebase.framework.spring.aop.advanced;

public class CustomerService {

    private String name;
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        System.out.println("Customer name: " + name);
        return name;
    }

    public void printUrl() {
        System.out.println("Customer url: " + url);
    }

    public void printThrowException() {
        throw new IllegalArgumentException("error");
    }
}
