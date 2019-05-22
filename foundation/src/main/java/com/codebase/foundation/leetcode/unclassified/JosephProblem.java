package com.codebase.foundation.leetcode.unclassified;

/**
 * 约瑟夫环
 *
 * @author chengxiaojun
 */
public class JosephProblem {

    public static void main(String[] args) {
        System.out.println(getLastPerson(3, 1));
        System.out.println(getLastPerson(3, 2));
        System.out.println(getLastPerson(3, 3));
        System.out.println(getLastPerson(5, 2));
        System.out.println(getLastPerson(5, 3));
        System.out.println(getLastPerson(5, 4));
    }

    public static int getLastPerson(int n, int k) {
        int result = 0;
        for (int round = n - 1; round >= 1; round--) {
            result = (result + k) % (n - (round - 1));
        }
        return result + 1;
    }
}
