package com.codebase.framework.spring.aop;

public class CustomerService {

    private String name;
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String printName() {
        System.out.println("Customer name: " + this.name);
        return name;
    }

    public void printUrl() {
        System.out.println("Customer url: " + this.url);
    }

    public void printThrowException() {
        throw new IllegalArgumentException();
    }

    public void printAround(String name){
        System.out.println("printAround() is running, args : " + name);
    }
}
