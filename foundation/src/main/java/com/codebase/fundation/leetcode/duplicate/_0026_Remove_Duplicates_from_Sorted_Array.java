package com.codebase.fundation.leetcode.duplicate;

public class _0026_Remove_Duplicates_from_Sorted_Array {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int startNum = nums[0];
        int length = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != startNum) {
                nums[length] = nums[i];
                startNum = nums[i];
                length++;
            }
        }

        return length;
    }

    public static void main(String[] args) {
        _0026_Remove_Duplicates_from_Sorted_Array main = new _0026_Remove_Duplicates_from_Sorted_Array();
        System.out.println(main.removeDuplicates(new int[]{1, 1, 2}));
        System.out.println(main.removeDuplicates(new int[]{1, 2, 2, 3, 4, 4}));
        System.out.println(main.removeDuplicates(new int[]{1, 2, 2}));
    }
}
