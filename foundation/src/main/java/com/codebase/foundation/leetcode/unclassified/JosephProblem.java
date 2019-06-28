package com.codebase.foundation.leetcode.unclassified;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环
 *
 * @author chengxiaojun
 */
public class JosephProblem {

    public static void main(String[] args) {

        System.out.println("==============");
        System.out.println(getLastPerson(3, 1));
        System.out.println(getLastPerson(3, 2));
        System.out.println(getLastPerson(3, 3));
        System.out.println(getLastPerson(5, 2));
        System.out.println(getLastPerson(5, 3));
        System.out.println(getLastPerson(5, 4));

        System.out.println("==============");
        System.out.println(getLastPerson2(3, 1));
        System.out.println(getLastPerson2(3, 2));
        System.out.println(getLastPerson2(3, 3));
        System.out.println(getLastPerson2(5, 2));
        System.out.println(getLastPerson2(5, 3));
        System.out.println(getLastPerson2(5, 4));
    }

    public static int getLastPerson2(int n, int k) {
        List<Integer> elemQueue = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            elemQueue.add(i);
        }

        int removePos = k % n;
        for (int round = 1; round < n; round++) {
            elemQueue.remove(removePos);
            removePos = (removePos + k) % elemQueue.size();
        }
        return elemQueue.get(0);
    }

    public static int getLastPerson(int n, int k) {
        int pos = 1;
        for (int round = n - 1; round >= 1; round--) {
            pos = (pos + k) % (n - round + 1) + 1;
        }
        return pos;
    }
}
