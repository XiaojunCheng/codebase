package com.codebase.fundation.leetcode.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0046_Permutations {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> solutions = new ArrayList<>();

        Arrays.sort(nums);
        do {
            List<Integer> s = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                s.add(nums[i]);
            }
            solutions.add(s);
        } while (Util.thift(nums));

        return solutions;
    }

    public static void main(String[] args) {
        _0046_Permutations main = new _0046_Permutations();
        List<List<Integer>> solutions = main.permute(new int[]{1, 2, 3, 4, 5, 6});
        for (List<Integer> s : solutions) {
            System.out.println(s);
        }
    }

}
