package com.codebase.foundation.apidesign.v2;


/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public class Functions {

    static class Counter implements Function<String, String> {

        private int count;

        public int getCount() {
            return count;
        }

        @Override
        public String map(String s) {
            count++;
            return s;
        }
    }

}
