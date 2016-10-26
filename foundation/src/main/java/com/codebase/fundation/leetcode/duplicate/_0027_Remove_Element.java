package com.codebase.fundation.leetcode.duplicate;

public class _0027_Remove_Element {

    public int removeElement(int[] nums, int val) {
        int count = 0;
        int i = 0;
        while (i < nums.length) {
            if (val == nums[i]) {
                count++;
                i++;
                while ((i < nums.length) && val != nums[i]) {
                    nums[i - count] = nums[i];
                    i++;
                }
            } else {
                i++;
            }
        }
        return nums.length - count;
    }

    public static void main(String[] args) {

        _0027_Remove_Element main = new _0027_Remove_Element();
        int[] arr = {3, 2, 2, 3};
        //int[] arr = {2, 3, 2, 2, 3};
        int size = main.removeElement(arr, 3);
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }
}
