package com.codebase.foundation.leetcode.unclassified;

import java.util.*;

public class _0049_Group_Anagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);
        }

        List<List<String>> solutions = new ArrayList<>();
        for (List<String> solution : map.values()) {
            solutions.add(solution);
        }
        return solutions;
    }

    public static void main(String[] args) {
        _0049_Group_Anagrams main = new _0049_Group_Anagrams();

        String[][] data = {
                {"eat", "tea", "tan", "ate", "nat", "bat"}//
        };

        for (int i = 0; i < data.length; i++) {
            List<List<String>> solutions = main.groupAnagrams(data[i]);
            System.out.println("============");
            for (List<String> s : solutions) {
                System.out.println(s);
            }
        }


    }

}
