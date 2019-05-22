package com.codebase.foundation.performance;

import java.util.HashMap;

public class Constants {

    public final static int ARRAY_LIMIT = 512 * 1024 * 1024;
    public final static String ARRAY_LIMIT_NAME = "512m";

    public final static int[] INT_ARRAY_1 = new int[ARRAY_LIMIT];
    public final static int[] INT_ARRAY_2 = new int[ARRAY_LIMIT];
    public final static int[] INT_ARRAY_3 = new int[ARRAY_LIMIT / 10];

    public final static String MAP_LIMIT_NAME = "1m";
    public final static int MAP_LIMIT = 1000000;

    public final static HashMap<Object, Object> MAP_1 = new HashMap<>((int) (MAP_LIMIT * 1.2), 0.9f);
    public final static HashMap<Object, Object> MAP_2 = new HashMap<>(MAP_LIMIT * 2, 0.5f);

    static {
        for (int i = 0; i < MAP_LIMIT; ++i) {
            Long val = (long) i;
            String sval = val.toString();

            MAP_1.put(val, val);
            MAP_1.put(sval, sval);

            MAP_2.put(val, val);
            MAP_2.put(sval, sval);
        }
    }

    public static String name(long loop) {
        if (loop > 1000 * 1000 * 1000) {
            return loop / 1000 / 1000 / 1000 + "g";
        }
        if (loop > 1000 * 1000) {
            return loop / 1000 / 1000 + "m";
        }
        if (loop > 1000) {
            return loop / 1000 / 1000 + "k";
        }
        return "" + loop;
    }
}
