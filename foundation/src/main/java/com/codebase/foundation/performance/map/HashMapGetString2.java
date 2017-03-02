package com.codebase.foundation.performance.map;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class HashMapGetString2 implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "hash-get-string2-" + Constants.MAP_LIMIT_NAME;

    public HashMapGetString2() {

    }

    public void init() throws Exception {

    }

    public Object action() throws Exception {
        for (long i = 0; i < Constants.MAP_LIMIT; ++i) {
            String k = Long.toString(i);
            Constants.MAP_2.get(k);
        }

        return Constants.MAP_LIMIT;
    }

}
