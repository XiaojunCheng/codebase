package com.codebase.framework.bytecode.bytebuddy.sample.secure;

import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;

public class SecurityInterceptor {

    public static void intercept(@Origin Method method) {
        Secured secured = method.getAnnotation(Secured.class);
        if (!secured.user().equals(UserHolder.user)) {
            throw new IllegalStateException("Wrong user");
        }
    }

}
