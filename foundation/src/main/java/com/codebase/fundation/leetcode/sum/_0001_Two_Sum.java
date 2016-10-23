package com.codebase.fundation.leetcode.sum;

import java.util.HashMap;
import java.util.Map;

public class _0001_Two_Sum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer/*value*/, Integer/*index*/> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num2 = target - nums[i];
            if (maps.containsKey(num2)) {
                return new int[] {maps.get(num2), i};
            } else {
                maps.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        //        int[] nums = {3, 2, 4};
        //        int[] result = twoSum(nums, 6);
        //        int[] nums = {0, 4, 3, 0};
        //        int[] result = twoSum(nums, 0);
        for (int r : result) {
            System.out.println(r);
        }
    }
}
