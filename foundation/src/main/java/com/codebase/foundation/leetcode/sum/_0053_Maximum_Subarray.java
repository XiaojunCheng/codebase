package com.codebase.foundation.leetcode.sum;

/**
 * Runtime: 6 ms, faster than 82.15% of Java online submissions for Maximum Subarray.
 * Memory Usage: 38.7 MB, less than 89.92% of Java online submissions for Maximum Subarray.
 */
public class _0053_Maximum_Subarray {

    public int maxSubArray(int[] nums) {
        int maxTotal = nums[0];
        int totalFactor = 0;
        for (int i = 0; i < nums.length; i++) {
            maxTotal = (nums[i] + totalFactor > maxTotal) ? (nums[i] + totalFactor) : maxTotal;
            totalFactor = (nums[i] + totalFactor > 0) ? nums[i] + totalFactor : 0;
        }
        return maxTotal;
    }

    public static void main(String[] args) {
        _0053_Maximum_Subarray main = new _0053_Maximum_Subarray();
        System.out.println(main.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(main.maxSubArray(new int[]{-2, -1}));
        System.out.println(main.maxSubArray(new int[]{-1}));
        System.out.println(main.maxSubArray(new int[]{1}));
    }

}
