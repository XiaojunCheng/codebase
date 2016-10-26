package com.codebase.fundation.leetcode.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _0039_Combination_Sum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        if (target < candidates[0]) {
            return Collections.emptyList();
        }

        List<List<Integer>> solutions = new ArrayList<>();
        if (target == candidates[0]) {
            List<Integer> solution = new ArrayList<>();
            solution.add(candidates[0]);
            solutions.add(solution);
            return solutions;
        }

        int end = candidates.length - 1;
        while (candidates[end] > target) {
            end--;
        }
        return combinationSum(candidates, end, target);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int end, int target) {

        if (target < candidates[0]) {
            return Collections.emptyList();
        }

        if (end == 0) {
            if (target % candidates[0] == 0) {
                List<List<Integer>> solutions = new ArrayList<>();
                List<Integer> solution = new ArrayList<>();
                for (int i = target / candidates[0]; i >= 1; i--) {
                    solution.add(candidates[0]);
                }
                solutions.add(solution);
                return solutions;
            } else {
                return Collections.emptyList();
            }
        }

        List<List<Integer>> solutions = new ArrayList<>();

        for (int i = target / candidates[end]; i >= 0; i--) {
            int mod = target - i * candidates[end];
            if (mod == 0) {
                List<Integer> solution = new ArrayList<>();
                for (int j = i; j >= 1; j--) {
                    solution.add(candidates[end]);
                }
                solutions.add(solution);
                continue;
            }

            List<List<Integer>> subSolutions = combinationSum(candidates, end - 1, mod);
            if (subSolutions.isEmpty()) {
                continue;
            }

            for (List<Integer> solution : subSolutions) {
                for (int j = i; j >= 1; j--) {
                    solution.add(candidates[end]);
                }
                solutions.add(solution);
            }
        }
        return solutions;
    }

    public static void main(String[] args) {

        int[][] data = {
                {2, 3, 6, 7},//
                {1, 2, 3, 6, 7},//
                {1}//
        };

        int[] targets = {
                7,//
                7,//
                1//
        };

        _0039_Combination_Sum main = new _0039_Combination_Sum();
        for (int i = 0; i < data.length; i++) {
            System.out.println("=================");
            List<List<Integer>> solutions = main.combinationSum(data[i], targets[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
        }
    }

}
