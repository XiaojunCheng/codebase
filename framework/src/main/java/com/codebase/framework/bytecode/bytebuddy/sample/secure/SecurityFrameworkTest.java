package com.codebase.framework.bytecode.bytebuddy.sample.secure;

import org.junit.Test;

public class SecurityFrameworkTest {

    @Test
    public void test() throws Exception {
        SecurityFramework framework = new SecurityFramework();
        SecureService service = framework.secure(SecureService.class);

        try {
            UserHolder.user = "user";
            service.doSensitiveAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserHolder.user = "ADMIN";
        service.doSensitiveAction();

        NoSecureService noSecureService = framework.secure(NoSecureService.class);
        noSecureService.sayHello("user");
    }
}
