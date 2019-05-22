package com.codebase.foundation.leetcode.dynamicprogrammin;

/**
 * Runtime: 11 ms, faster than 77.69% of Java online submissions for Shortest Unsorted Continuous Subarray.
 * Memory Usage: 40.9 MB, less than 89.23% of Java online submissions for Shortest Unsorted Continuous Subarray.
 */
public class _0581_ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] <= nums[i]) {
                continue;
            }
            flag = true;
        }

        if (flag == false) {
            return 0;
        }

        int end = nums.length - 1;
        int start = 0;

        int[] max = new int[nums.length];
        max[0] = nums[0];
        for (int i = 1; i <= end; i++) {
            max[i] = Math.max(max[i - 1], nums[i]);
        }
        while (end > start) {
            if (max[end - 1] <= nums[end]) {
                end--;
            } else {
                break;
            }
        }

        max[end] = nums[end];
        for (int i = end - 1; i >= start; i--) {
            max[i] = Math.min(max[i + 1], nums[i]);
        }
        while (start < end) {
            if (nums[start] <= max[start + 1]) {
                start++;
            } else {
                break;
            }
        }
        return end - start + 1;
    }

    public static void main(String[] args) {
        _0581_ShortestUnsortedContinuousSubarray main = new _0581_ShortestUnsortedContinuousSubarray();
        System.out.println(main.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(main.findUnsortedSubarray(new int[]{2, 1}));
        System.out.println(main.findUnsortedSubarray(new int[]{1, 2, 3, 5, 4}));
    }
}