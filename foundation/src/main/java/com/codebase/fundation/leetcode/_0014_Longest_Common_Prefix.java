package com.codebase.fundation.leetcode;

public class _0014_Longest_Common_Prefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        int commonLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < commonLength) {
                commonLength = strs[i].length();
            }
        }

        int longestCommonPrefix = 0;
        for (int i = 0; i < commonLength; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (ch != strs[j].charAt(i)) {
                    return strs[0].substring(0, longestCommonPrefix);
                }
            }
            longestCommonPrefix++;
        }

        return strs[0].substring(0, longestCommonPrefix);
    }

    public static void main(String[] args) {
        _0014_Longest_Common_Prefix main = new _0014_Longest_Common_Prefix();
        System.out.println(main.longestCommonPrefix(new String[]{"abcd", "abde"}));
    }
}
