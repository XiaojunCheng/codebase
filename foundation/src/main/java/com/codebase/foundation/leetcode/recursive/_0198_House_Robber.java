package com.codebase.foundation.leetcode.recursive;

/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
 * Memory Usage: 35.5 MB, less than 92.14% of Java online submissions for House Robber.
 */
public class _0198_House_Robber {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int max4Pre = nums[0];
        int max4Cur = Math.max(nums[0], nums[1]);
        int max = 0;
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(nums[i] + max4Pre, max4Cur);
            max4Pre = max4Cur;
            max4Cur = max;
        }

        return max;
    }

    public static void main(String[] args) {
        _0198_House_Robber main = new _0198_House_Robber();
        System.out.println(main.rob(new int[]{1}));
        System.out.println(main.rob(new int[]{1, 2, 3, 1}));
        System.out.println(main.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(main.rob(new int[]{1, 2, 3, 1, 1, 4}));
    }

}
