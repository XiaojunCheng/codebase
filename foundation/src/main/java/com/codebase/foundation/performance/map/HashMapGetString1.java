package com.codebase.foundation.performance.map;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class HashMapGetString1 implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "hash-get-string1-" + Constants.MAP_LIMIT_NAME;

    public HashMapGetString1() {

    }

    public void init() throws Exception {

    }

    public Object action() throws Exception {
        for (long i = 0; i < Constants.MAP_LIMIT; ++i) {
            String k = Long.toString(i);
            Constants.MAP_1.get(k);
        }

        return Constants.MAP_LIMIT;
    }

}
