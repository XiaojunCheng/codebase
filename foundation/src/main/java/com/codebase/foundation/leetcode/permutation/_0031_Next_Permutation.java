package com.codebase.foundation.leetcode.permutation;


public class _0031_Next_Permutation {

    public void nextPermutation(int[] nums) {
        if (!Util.thift(nums)) {
            Util.reverse(nums, 0, nums.length);
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 3},// → 1,3,2
                {3, 2, 1},// → 1,2,3
                {1, 1, 5},// → 1,5,1
                {1, 3, 2, 2}// → 2, 1, 2, 3
        };

        _0031_Next_Permutation main = new _0031_Next_Permutation();
        for (int i = 0; i < data.length; i++) {
            main.nextPermutation(data[i]);
            System.out.println("=============================");
            for (int j : data[i]) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

}
