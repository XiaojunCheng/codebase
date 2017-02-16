package com.codebase.foundation.annotation;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        Book book = new Book();

        //通过运行时反射API获得annotation信息
        Method[] methods = book.getClass().getMethods();

        boolean flag = book.getClass().isAnnotationPresent(Description.class);

        if (flag) {
            Description description = (Description) book.getClass().getAnnotation(Description.class);
            System.out.println("Description--->" + description.value());
            for (Method method : methods) {
                if (method.isAnnotationPresent(Author.class)) {
                    Author author = (Author) method.getAnnotation(Author.class);
                    System.out.println("Author--->" + author.name() + " from " + author.group());
                }
            }
        }

    }

}
