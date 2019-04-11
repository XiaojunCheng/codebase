package com.codebase.framework.bytecode.bytebuddy.sample.secure;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {

    String user();

}
