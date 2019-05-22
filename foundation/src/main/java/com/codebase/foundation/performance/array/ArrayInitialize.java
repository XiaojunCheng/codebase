package com.codebase.foundation.performance.array;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class ArrayInitialize implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "array-init-" + Constants.ARRAY_LIMIT_NAME;

    public ArrayInitialize() {

    }

    public void init() throws Exception {

    }

    public Object action() throws Exception {
        int len = Constants.INT_ARRAY_1.length;
        for (int i = 0; i < len; ++i) {
            Constants.INT_ARRAY_1[i] = i;
        }
        return len;
    }

}
