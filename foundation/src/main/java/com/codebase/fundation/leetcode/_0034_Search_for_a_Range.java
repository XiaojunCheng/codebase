package com.codebase.fundation.leetcode;

import java.util.Arrays;

public class _0034_Search_for_a_Range {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.fill(result, -1);

        if (nums.length == 0 || (nums.length == 1 && nums[0] != target)) {
            return result;
        }

        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == target) {
                result[0] = mid;
                result[1] = mid;
                for (int index = mid - 1; index >= low; index--) {//往前找
                    if (nums[index] == target) {
                        result[0]--;
                        continue;
                    }
                    break;
                }
                for (int index = mid + 1; index < nums.length; index++) {//往后找
                    if (nums[index] == target) {
                        result[1]++;
                        continue;
                    }
                    break;
                }
                return result;
            }

            if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }

        if (nums[low] == target && nums[high] == target) {
            result[0] = low;
            result[1] = high;
            return result;
        }

        if (nums[low] == target) {
            Arrays.fill(result, low);
            return result;
        }

        if (nums[high] == target) {
            Arrays.fill(result, high);
            return result;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] data = {
                {5, 7, 7, 8, 8, 10},//
                {1}//
        };

        int[] targets = {
                8,//
                0//
        };

        _0034_Search_for_a_Range main = new _0034_Search_for_a_Range();
        for (int i = 0; i < data.length; i++) {
            int[] result = main.searchRange(data[i], targets[i]);
            System.out.println(result[0] + " -> " + result[1]);
        }

    }

}
