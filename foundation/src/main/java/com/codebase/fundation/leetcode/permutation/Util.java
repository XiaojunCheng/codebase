package com.codebase.fundation.leetcode.permutation;

public class Util {

    public static boolean thift(int[] nums) {
        int thiftPos = nums.length - 2;
        while (thiftPos >= 0) {
            if (nums[thiftPos] >= nums[thiftPos + 1]) {
                thiftPos--;
            } else {
                break;
            }
        }

        if (thiftPos == -1) {
            return false;
        }

        for (int i = nums.length - 1; i > thiftPos; i--) {
            if (nums[i] > nums[thiftPos]) {
                int tmp = nums[thiftPos];
                nums[thiftPos] = nums[i];
                nums[i] = tmp;
                reverse(nums, thiftPos + 1, nums.length);
                break;
            }
        }

        return true;
    }

    /**
     * [start, end) 半闭半开区间
     */
    public static void reverse(int[] nums, int start, int end) {
        int length = end - start;
        if (length == 1) {
            return;
        }

        int count = (length + 1) / 2;
        int tmp;
        for (int i = start; i < start + count; i++) {
            tmp = nums[start + end - 1 - i];
            nums[start + end - 1 - i] = nums[i];
            nums[i] = tmp;
        }
    }
}
