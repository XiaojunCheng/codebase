package com.codebase.fundation.leetcode.sum;

import java.util.Arrays;

public class _0016_3Sum_Closest {

    public int threeSumClosest(int[] nums, int target) {

        if (nums.length <= 2) {
            return 0;
        }

        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }

        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        int minAlpha = Math.abs(closestSum - target);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int alpha = Math.abs(sum - target);
                    if (alpha < minAlpha) {
                        closestSum = sum;
                        minAlpha = alpha;
                    }
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        _0016_3Sum_Closest main = new _0016_3Sum_Closest();
        int[][] data = {//
                {-1, 2, 1, -4},//2
                {0, 2, 1, -3}//0
        };

        int[] targets = {
                1,//
                1//
        };

        for (int i = 0; i < data.length; i++) {
            System.out.println(main.threeSumClosest(data[i], targets[i]));
        }

    }

}
