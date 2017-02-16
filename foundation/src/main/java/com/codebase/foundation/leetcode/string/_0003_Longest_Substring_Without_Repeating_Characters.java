package com.codebase.foundation.leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class _0003_Longest_Substring_Without_Repeating_Characters {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set<Character> charSet = new HashSet<>();
        int i = 0;
        int j = 0;
        int maxLength = 0;
        while (i < length && j < length) {
            Character ch = s.charAt(j);
            if (!charSet.contains(ch)) {
                charSet.add(ch);
                j++;
                maxLength = Math.max(j - i, maxLength);
            } else {
                charSet.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        _0003_Longest_Substring_Without_Repeating_Characters instance = new _0003_Longest_Substring_Without_Repeating_Characters();
        System.out.println(instance.lengthOfLongestSubstring("abcdefg"));
        System.out.println(instance.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(instance.lengthOfLongestSubstring("bbbbb"));
        System.out.println(instance.lengthOfLongestSubstring("pwwkew"));
        System.out.println(instance.lengthOfLongestSubstring("ohomm"));
        System.out.println(instance.lengthOfLongestSubstring("xxzqi"));
        System.out.println(instance.lengthOfLongestSubstring("fcjhzgnxoxauecmmeufpljfpacrazaneewndecbuzbrgffsj"));
    }
}
