package com.codebase.fundation.leetcode.sum;

import java.util.*;

public class _0018_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> solutions = new ArrayList<>();
        if (nums.length <= 3) {
            return solutions;
        }

        Arrays.sort(nums);
        if (nums[0] * 4 > target || nums[nums.length - 1] * 4 < target) {
            return solutions;
        }

        for (int one = 0; one <= nums.length - 4; one++) {
            if (one > 0 && nums[one] == nums[one - 1]) {
                continue;
            }

            if (nums[one] * 4 > target) {
                one = nums.length - 3;
                continue;
            }

            for (int two = one + 1; two <= nums.length - 3; two++) {
                if (two > one + 1 && nums[two] == nums[two - 1]) {
                    continue;
                }

                if (nums[one] + nums[two] * 3 > target) {
                    two = nums.length - 2;
                    continue;
                }

                int sum = target - nums[one] - nums[two];
                Set<Integer> candidates = new HashSet<>();
                Set<Integer> needRemove = new HashSet<>();
                candidates.add(nums[two + 1]);
                for (int four = two + 2; four <= nums.length - 1; four++) {
                    if (!candidates.contains(sum - nums[four])) {
                        if (nums[one] + nums[two] + nums[two + 1] + nums[four] > target) {
                            four = nums.length;
                            continue;
                        }
                        candidates.add(nums[four]);
                        continue;
                    }

                    if (needRemove.contains(nums[four])) {
                        continue;
                    }

                    solutions.add(Arrays.asList(nums[one], nums[two], sum - nums[four], nums[four]));
                    needRemove.add(nums[four]);
                    needRemove.add(sum - nums[four]);
                }
            }
        }

        return solutions;
    }

    public static void main(String[] args) {
        _0018_4Sum main = new _0018_4Sum();
        int[][] data = {
                {1, 0, -1, 0, -2, 2},//
                {-1, 0, 1, 2, -1, -4},//
                {0, 4, -5, 2, -2, 4, 2, -1, 4},//
                {-3, -2, -1, 0, 0, 1, 2, 3},//
                {0, 1, 5, 0, 1, 5, 5, -4}
        };
        int[] targets = {
                0, //
                -1,//
                12,//
                0,//
                11//
        };
        for (int i = 0; i < data.length; i++) {
            System.out.println("============");
            List<List<Integer>> solutions = main.fourSum(data[i], targets[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
        }

    }
}
