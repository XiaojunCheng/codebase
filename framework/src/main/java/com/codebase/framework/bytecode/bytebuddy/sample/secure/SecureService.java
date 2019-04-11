package com.codebase.framework.bytecode.bytebuddy.sample.secure;

public class SecureService {

    @Secured(user = "ADMIN")
    public void doSensitiveAction() {
        //运行敏感代码...
        System.out.println("computer is shutting down...");
    }
}