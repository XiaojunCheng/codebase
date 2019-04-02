package com.codebase.foundation.leetcode.unclassified;

public class _0560_Subarray_Sum_Equals_K {

    /**
     * Runtime: 127 ms, faster than 22.93% of Java online submissions for Subarray Sum Equals K.
     * Memory Usage: 40.2 MB, less than 45.95% of Java online submissions for Subarray Sum Equals K.
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return k == nums[0] ? 1 : 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int total = 0;
            for (int j = i; j < nums.length; j++) {
                total += nums[j];
                if (total == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
