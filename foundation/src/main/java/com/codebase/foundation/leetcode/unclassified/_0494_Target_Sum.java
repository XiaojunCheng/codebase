package com.codebase.foundation.leetcode.unclassified;

public class _0494_Target_Sum {

    /**
     * Runtime: 164 ms, faster than 50.78% of Java online submissions for Target Sum.
     * Memory Usage: 35.9 MB, less than 95.86% of Java online submissions for Target Sum.
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return findTargetSumWays(nums, 0, nums.length - 1, S);
    }

    private int findTargetSumWays(int[] nums, int start, int end, int S) {
        if (end == start) {
            return (Math.abs(nums[0]) == Math.abs(S)) ? ((S == 0) ? 2 : 1) : 0;
        }

        if (nums[end] == 0) {
            return findTargetSumWays(nums, start, end - 1, S) * 2;
        }

        return findTargetSumWays(nums, start, end - 1, S - nums[end]) + findTargetSumWays(nums, start, end - 1, S + nums[end]);
    }
}
