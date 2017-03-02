package com.codebase.foundation.performance.array;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class ArrayForCopy implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "array-copy-forcpy-" + Constants.ARRAY_LIMIT_NAME;

    public void init() throws Exception {
    }

    public Object action() throws Exception {
        int len = Constants.INT_ARRAY_1.length;
        int[] a = Constants.INT_ARRAY_1;
        int[] b = Constants.INT_ARRAY_2;
        for (int i = 0; i < len; ++i) {
            b[i] = a[i];
        }
        return len;
    }

}
