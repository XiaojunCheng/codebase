package com.codebase.foundation.leetcode.sum;

import java.util.*;

/**
 * 相关问题: 0001 Two Sum
 * 47 ms
 */
public class _0015_3Sum {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> solutions = new ArrayList<>();
        if (nums.length <= 2) {
            return solutions;
        }

        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        Collections.sort(numList);

        Map<Integer/*value*/, List<Integer/*index*/>> valueIndex = new HashMap<>();
        for (int i = 0; i < numList.size(); i++) {
            int value = numList.get(i);
            if (!valueIndex.containsKey(value)) {
                valueIndex.put(value, new ArrayList<Integer>());
            }
            valueIndex.get(value).add(i);
        }

        for (int i = 0; i < numList.size() - 1; i++) {
            int one = numList.get(i);
            if (i > 0 && one == numList.get(i - 1)) {
                continue;
            }
            if (one > 0) {
                continue;
            }
            for (int j = i + 1; j < numList.size(); j++) {
                int two = numList.get(j);
                if (j > i + 1 && two == numList.get(j - 1)) {
                    continue;
                }

                int three = -one - two;
                if (one + two > 0 || three < two) {
                    continue;
                }

                if (!valueIndex.containsKey(three)) {
                    continue;
                }

                List<Integer> threeIndex = valueIndex.get(three);
                int size = (one == three ? 1 : 0) + (two == three ? 1 : 0);
                if (threeIndex.size() > size) {
                    List<Integer> solution = new ArrayList<>();
                    solution.add(one);
                    solution.add(two);
                    solution.add(three);
                    solutions.add(solution);
                }
            }
        }

        return solutions;
    }

    public static void main(String[] args) {
        _0015_3Sum main = new _0015_3Sum();
        int[][] data = {//
//                {-1},//
//                {-1, 0},//
//                {-1, 0, 0},//
//                {-1, 0, 1},//
//                {0, 0, 0},//
                {-1, 0, 1, 2, -1, -4},//
                {7, -1, 14, -12, -8, 7, 2, -15, //
                        8, 8, -8, -14, -4, -5, 7, 9, 11, //
                        -4, -15, -6, 1, -14, 4, 3, 10, //
                        -5, 2, 1, 6, 11, 2, -2, -5, -7, //
                        -6, 2, -15, 11, -6, 8, -4, 2, 1, //
                        -1, 4, -6, -15, 1, 5, -15, 10, 14, //
                        9, -8, -6, 4, -6, 11, 12, -15, 7, //
                        -1, -9, 9, -1, 0, -4, -1, -12, -2, //
                        14, -9, 7, 0, -3, -4, 1, -2, 12, //
                        14, -10, 0, 5, 14, -1, 14, 3, 8, //
                        10, -8, 8, -5, -2, 6, -11, 12, //
                        13, -7, -12, 8, 6, -13, 14, -2, //
                        -5, -11, 1, 3, -6}//
        };
        for (int i = 0; i < data.length; i++) {
            System.out.println("======================");
            List<List<Integer>> solutions = main.threeSum(data[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
        }

    }
}
