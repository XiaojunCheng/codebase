package com.codebase.foundation.leetcode.set;

import java.util.HashSet;
import java.util.Set;

/**
 * Runtime: 7 ms, faster than 95.90% of Java online submissions for Jewels and Stones.
 * Memory Usage: 38.2 MB, less than 5.36% of Java online submissions for Jewels and Stones.
 */
public class _0771_JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        if (J.isEmpty() || S.isEmpty()) {
            return 0;
        }

        Set<Character> uniqJ = new HashSet<>();
        for (int i = J.length() - 1; i >= 0; i--) {
            uniqJ.add(J.charAt(i));
        }

        int count = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (uniqJ.contains(S.charAt(i))) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _0771_JewelsAndStones main = new _0771_JewelsAndStones();
        System.out.println(main.numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(main.numJewelsInStones("z", "ZZ"));
    }

}
