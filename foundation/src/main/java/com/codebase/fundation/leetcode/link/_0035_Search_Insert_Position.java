package com.codebase.fundation.leetcode.link;

public class _0035_Search_Insert_Position {

    public int searchInsert(int[] nums, int target) {

        if (nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    public static void main(String[] args) {
        _0035_Search_Insert_Position main = new _0035_Search_Insert_Position();
        System.out.println(main.searchInsert(new int[]{1, 3, 5, 6}, 5));//2
        System.out.println(main.searchInsert(new int[]{1, 3, 5, 6}, 2));//1
        System.out.println(main.searchInsert(new int[]{1, 3, 5, 6}, 7));//4
        System.out.println(main.searchInsert(new int[]{1, 3, 5, 6}, 0));//0
    }
}
