package com.codebase.framework.bytecode.bytebuddy.sample.secure;

public class NoSecureService {

    @Secured(user = "*")
    public void sayHello(String name) {
        System.out.println("hello: " + name);
    }
}
