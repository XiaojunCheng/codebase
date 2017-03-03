package com.codebase.foundation.instrumentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/2.
 */
public class TransformerClass {

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public String getDateTime() {
        return "after: " + format.format(System.currentTimeMillis());
    }

}
