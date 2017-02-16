package com.codebase.foundation.leetcode.sum;

import java.util.*;

public class _0040_Combination_Sum_II {


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        if (target < candidates[0]) {
            return Collections.emptyList();
        }

        List<List<Integer>> solutions = new ArrayList<>();
        if (target == candidates[0]) {
            List<Integer> solution = new ArrayList<>(1);
            solution.add(candidates[0]);
            solutions.add(solution);
            return solutions;
        }

        List<Integer> counts = removeDuplicates(candidates, target);
        return combinationSum2(candidates, counts.size() - 1, target, counts);
    }

    public static List<Integer> removeDuplicates(int[] nums, int target) {

        List<Integer> counts = new ArrayList<>(nums.length);

        if (nums.length <= 1) {
            counts.add(1);
            return counts;
        }

        int end = nums.length - 1;
        while (nums[end] > target) {
            end--;
        }

        int currentNum = nums[0];
        int currentPos = 1;
        int currentCount = 1;
        for (int i = 1; i <= end; i++) {
            if (nums[i] != currentNum) {
                counts.add(currentCount);
                nums[currentPos] = nums[i];
                currentNum = nums[i];
                currentPos++;
                currentCount = 1;
            } else {
                currentCount++;
            }
        }
        counts.add(currentCount);

        return counts;
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int end, int target, List<Integer> counts) {

        if (end < 0 || target < candidates[0]) {
            return Collections.emptyList();
        }

        if (target < candidates[end]) {
            return combinationSum2(candidates, end - 1, target, counts);
        }

        int count = counts.get(end);
        if (end == 0) {
            if ((target % candidates[end] == 0) && (target / candidates[end] <= count)) {
                List<List<Integer>> solutions = new ArrayList<>();
                List<Integer> solution = new ArrayList<>();
                for (int i = target / candidates[end]; i >= 1; i--) {
                    solution.add(candidates[end]);
                }
                solutions.add(solution);
                return solutions;
            }
            return Collections.emptyList();
        }

        List<List<Integer>> solutions = new ArrayList<>();
        for (int addCount = Math.min(count, target / candidates[end]); addCount >= 0; addCount--) {
            int mod = target - addCount * candidates[end];
            if (mod == 0) {
                List<Integer> solution = new ArrayList<>();
                for (int i = addCount; i >= 1; i--) {
                    solution.add(candidates[end]);
                }
                solutions.add(solution);
                continue;
            }

            List<List<Integer>> subSolutions = combinationSum2(candidates, end - 1, mod, counts);
            if (subSolutions.isEmpty()) {
                continue;
            }

            for (List<Integer> solution : subSolutions) {
                for (int j = addCount; j >= 1; j--) {
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

        for (int i = 0; i < data.length; i++) {
            System.out.println("=================");
            List<List<Integer>> solutions = combinationSum2(data[i], targets[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
        }
    }
}
