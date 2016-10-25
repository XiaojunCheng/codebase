package com.codebase.fundation.leetcode.duplicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0442_Find_All_Duplicates_in_an_Array {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return duplicates;
        }

        Arrays.sort(nums);

        int startNum = nums[0];
        int length = 1;
        for (int i = 1; i < nums.length; ) {
            if (nums[i] == startNum) {
                duplicates.add(startNum);
                i++;
                while (i < nums.length && nums[i] == startNum) {
                    i++;
                }
            } else {
                nums[length] = nums[i];
                startNum = nums[i];
                length++;
                i++;
            }
        }

        return duplicates;
    }


    public static void main(String[] args) {
        _0442_Find_All_Duplicates_in_an_Array main = new _0442_Find_All_Duplicates_in_an_Array();

        int[][] data = {//
                {1},//[]
                {1, 2, 1},//[1]
                {1, 4, 2, 3, 2, 4},//[2,4]
                {1, 2, 2},//[2]
                {4, 3, 2, 7, 8, 2, 3, 1}//[2,3]
        };

        for (int i = 0; i < data.length; i++) {
            List<Integer> duplicates = main.findDuplicates(data[i]);
            System.out.println(duplicates);
        }

    }
}
