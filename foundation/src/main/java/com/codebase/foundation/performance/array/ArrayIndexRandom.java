package com.codebase.foundation.performance.array;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.Random;

public class ArrayIndexRandom implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "array-index-random-" + Constants.ARRAY_LIMIT_NAME;

    final int[] a = Constants.INT_ARRAY_1;
    final int[] b = Constants.INT_ARRAY_3;
    final int range = Constants.INT_ARRAY_3.length;

    public Object action() throws Exception {
        int len = range;
        for (int i = 0; i < len; ++i) {
            a[b[i]] = i;
        }
        return len;
    }

    public void init() throws Exception {
        Random r = new Random();
        for (int i = 0; i < range; ++i) {
            b[i] = i;
        }
        // shuffle
        for (int i = 0; i < range; ++i) {
            int x = r.nextInt(range - i) + i;
            int t = b[i];
            b[i] = b[x];
            b[x] = t;
        }
    }



}
