package com.codebase.foundation.performance.map;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class HashMapGetLong2 implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "hash-get-long2-" + Constants.MAP_LIMIT_NAME;

    public HashMapGetLong2() {

    }

    public void init() throws Exception {

    }

    public Object action() throws Exception {
        for (long i = 0; i < Constants.MAP_LIMIT; ++i) {
            Constants.MAP_2.get(i);
        }

        return Constants.MAP_LIMIT;
    }

}
