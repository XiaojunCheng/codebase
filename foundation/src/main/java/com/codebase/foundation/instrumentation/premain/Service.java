package com.codebase.foundation.instrumentation.premain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/2.
 */
public class Service {

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public String getServiceName() {
        return "hello: " + format.format(System.currentTimeMillis());
    }

}
