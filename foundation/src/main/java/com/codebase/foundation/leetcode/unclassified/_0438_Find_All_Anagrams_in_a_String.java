package com.codebase.foundation.leetcode.unclassified;

import java.util.*;

/**
 * Runtime: 14 ms, faster than 62.35% of Java online submissions for Find All Anagrams in a String.
 * Memory Usage: 39.4 MB, less than 72.20% of Java online submissions for Find All Anagrams in a String.
 */
public class _0438_Find_All_Anagrams_in_a_String {

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return Collections.emptyList();
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        Set<Character> pUniqChs = new HashSet<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pUniqChs.add(ch);
            pCount[ch - 'a']++;
        }

        List<Integer> ret = new ArrayList<>();
        int sStart = 0;
        for (int sEnd = sStart + 1; sEnd < s.length(); sEnd++) {
            char ch = s.charAt(sEnd);
            if (!pUniqChs.contains(ch)) {
                ret.addAll(findAnagrams(p.length(), pCount, sCount, s, sStart, sEnd - 1));
                sStart = sEnd + 1;
            }
        }

        if (sStart < s.length()) {
            ret.addAll(findAnagrams(p.length(), pCount, sCount, s, sStart, s.length() - 1));
        }

        return ret;
    }

    private List<Integer> findAnagrams(int pLen, int[] pCount, int[] sCount, String s, int sStart, int sEnd) {
        if (sEnd - sStart + 1 < pLen) {
            return Collections.emptyList();
        }

        List<Integer> ret = new ArrayList<>();
        /**
         * 清理状态
         */
        int diffCount = 0;
        for (int i = 0; i < pCount.length; i++) {
            sCount[i] = -pCount[i];
            if (sCount[i] != 0) {
                diffCount++;
            }
        }

        for (int i = 0; i < pLen; i++) {
            char ch = s.charAt(i + sStart);
            sCount[ch - 'a']++;
            if (sCount[ch - 'a'] == 0) {
                diffCount--;
            }

            if (sCount[ch - 'a'] == 1) {
                diffCount++;
            }
        }
        if (diffCount == 0) {
            ret.add(sStart);
        }

        for (int i = pLen; i < sEnd - sStart + 1; i++) {
            char curCh = s.charAt(i + sStart);
            sCount[curCh - 'a']++;
            if (sCount[curCh - 'a'] == 0) {
                diffCount--;
            }
            if (sCount[curCh - 'a'] == 1) {
                diffCount++;
            }

            char preCh = s.charAt(i - pLen + sStart);
            sCount[preCh - 'a']--;
            if (sCount[preCh - 'a'] == 0) {
                diffCount--;
            }
            if (sCount[preCh - 'a'] == -1) {
                diffCount++;
            }

            if (diffCount == 0) {
                ret.add(sStart + i - pLen + 1);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        _0438_Find_All_Anagrams_in_a_String main = new _0438_Find_All_Anagrams_in_a_String();
        System.out.println(main.findAnagrams("cbaebabacd", "abc"));
        System.out.println(main.findAnagrams("abab", "ab"));
    }

}
