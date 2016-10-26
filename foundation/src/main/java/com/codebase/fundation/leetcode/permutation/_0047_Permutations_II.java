package com.codebase.fundation.leetcode.permutation;

import java.util.List;

/**
 * 答案同46
 */
public class _0047_Permutations_II {

    public static void main(String[] args) {

        int[][] data = {
                {1, 1, 2},//
                {1, 3, 2, 2}//
        };

        _0046_Permutations main = new _0046_Permutations();
        for (int i = 0; i < data.length; i++) {
            System.out.println("=============================");
            List<List<Integer>> solutions = main.permute(data[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
