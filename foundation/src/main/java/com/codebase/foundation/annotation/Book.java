package com.codebase.foundation.annotation;

@Description(value = "about history")
public class Book {

    @Author(name = "myName")
    public String content() {
        return "work over!";
    }

}
