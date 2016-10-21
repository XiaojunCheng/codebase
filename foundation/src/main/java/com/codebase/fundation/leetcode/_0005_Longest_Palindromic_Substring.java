package com.codebase.fundation.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0005_Longest_Palindromic_Substring {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }

        //统计
        Map<Integer, Integer> candidates = new HashMap<>();
        for (int i = 0; i < length - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) {
                candidates.put(i, i + 2);
            }
        }

        for (int i = 1, start = 0; i < length; i++) {
            while (i < length && s.charAt(start) == s.charAt(i)) {
                i++;
            }
            if (i - 1 > start) {
                candidates.put(start, i - 1);
            }
            start = i;
        }

        Map<Integer, Integer> solutions = new HashMap<>();
        for (Map.Entry<Integer, Integer> candidateEntry : candidates.entrySet()) {
            int start = candidateEntry.getKey();
            int end = candidateEntry.getValue();
            while (start > 0 && end < length - 1) {
                if (s.charAt(start - 1) == s.charAt(end + 1)) {
                    start--;
                    end++;
                } else {
                    break;
                }
            }
            Integer value = solutions.get(start);
            if (value != null) {
                solutions.put(start, Math.max(end, value));
            } else {
                solutions.put(start, end);
            }
        }

        int maxLength = 1;
        int startPos = 0;
        for (Map.Entry<Integer, Integer> solutionEntry : solutions.entrySet()) {
            int start = solutionEntry.getKey();
            int end = solutionEntry.getValue();
            int distance = end - start + 1;
            if (distance > maxLength || (distance == maxLength && start < startPos)) {
                maxLength = distance;
                startPos = start;
            }
        }

        return s.substring(startPos, startPos + maxLength);
    }

    public static void main(String[] args) {
        _0005_Longest_Palindromic_Substring main = new _0005_Longest_Palindromic_Substring();
        System.out.println("bb".equals(main.longestPalindrome("bb")));
        System.out.println("a".equals(main.longestPalindrome("abc")));
        System.out.println("bcb".equals(main.longestPalindrome("abcbdb")));
        System.out.println("cbbc".equals(main.longestPalindrome("abcbbc")));
        System.out.println("cbbc".equals(main.longestPalindrome("abcbbccaaab")));
    }
}
