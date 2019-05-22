package com.codebase.foundation.leetcode.match;

public class _0028_Implement_strStr {

    public int strStr(String haystack, String needle) {

        if (needle.isEmpty()) {
            return 0;
        }

        if (haystack.isEmpty() || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        _0028_Implement_strStr main = new _0028_Implement_strStr();
        System.out.println(main.strStr("", ""));
        System.out.println(main.strStr("abcbc", "ab"));
        System.out.println(main.strStr("abcbc", "bc"));
        System.out.println(main.strStr("abcbc", "cbc"));
        System.out.println(main.strStr("abcbc", "abcbc"));
    }

}
