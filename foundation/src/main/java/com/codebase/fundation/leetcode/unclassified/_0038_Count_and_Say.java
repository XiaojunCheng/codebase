package com.codebase.fundation.leetcode.unclassified;

import java.util.HashMap;
import java.util.Map;

public class _0038_Count_and_Say {

    public String countAndSay(int n) {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");

        int k = 2;
        while (!map.containsKey(n)) {
            StringBuilder builder = new StringBuilder();

            String candidate = map.get(k - 1);
            char startChar = candidate.charAt(0);
            int count = 1;
            for (int i = 1; i < candidate.length(); i++) {
                char ch = candidate.charAt(i);
                if (startChar != ch) {
                    builder.append(Integer.toString(count));
                    builder.append(startChar);
                    startChar = ch;
                    count = 1;
                } else {
                    count++;
                }
            }
            builder.append(Integer.toString(count));
            builder.append(startChar);

            map.put(k, builder.toString());
            k++;
        }
        return map.get(n).toString();
    }


    public static void main(String[] args) {
        _0038_Count_and_Say main = new _0038_Count_and_Say();
        for (int i = 1; i <= 6; i++) {
            System.out.println(main.countAndSay(i));
        }
    }
}
