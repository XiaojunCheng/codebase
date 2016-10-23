package com.codebase.fundation.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _0003_Longest_Substring_Without_Repeating_Characters_2 {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> positions = new HashMap<>();
        int length = s.length();
        int maxLength = 0;
        int i = 0;
        int j = 0;
        while (i < length && j < length) {
            Character ch = s.charAt(j);
            if (positions.containsKey(ch)) {
                i = Math.max(i, positions.get(ch) + 1);
            }
            positions.put(ch, j);
            j++;
            maxLength = Math.max(j - i, maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        _0003_Longest_Substring_Without_Repeating_Characters_2 instance = new _0003_Longest_Substring_Without_Repeating_Characters_2();
        System.out.println(instance.lengthOfLongestSubstring("abcdefg"));
        System.out.println(instance.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(instance.lengthOfLongestSubstring("bbbbb"));
        System.out.println(instance.lengthOfLongestSubstring("pwwkew"));
        System.out.println(instance.lengthOfLongestSubstring("ohomm"));
        System.out.println(instance.lengthOfLongestSubstring("xxzqi"));
        System.out.println(instance.lengthOfLongestSubstring("fcjhzgnxoxauecmmeufpljfpacrazaneewndecbuzbrgffsj"));
    }
}
