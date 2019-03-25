package com.codebase.foundation.leetcode.dynamicprogrammin;

/**
 * Runtime: 204 ms, faster than 5.06% of Java online submissions for Shortest Unsorted Continuous Subarray.
 * Memory Usage: 59.6 MB, less than 5.18% of Java online submissions for Shortest Unsorted Continuous Subarray.
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
        while (end > start) {
            if (getMax(nums, start, end - 1) <= nums[end]) {
                end--;
            } else {
                break;
            }
        }

        while (start < end) {
            if (nums[start] <= getMin(nums, start + 1, end)) {
                start++;
            } else {
                break;
            }
        }
        return end - start + 1;
    }

    private int getMin(int[] nums, int start, int end) {
        int min = nums[start];
        for (int i = start + 1; i <= end; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }

    private int getMax(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        _0581_ShortestUnsortedContinuousSubarray main = new _0581_ShortestUnsortedContinuousSubarray();
        System.out.println(main.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }
}