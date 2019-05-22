package com.codebase.foundation.leetcode.unclassified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _0448_Find_All_Numbers_Disappeared_in_an_Array {

    /**
     * 空间复杂度O(n)
     * Runtime: 7 ms, faster than 90.90% of Java online submissions for Find All Numbers Disappeared in an Array.
     * Memory Usage: 51.1 MB, less than 31.90% of Java online submissions for Find All Numbers Disappeared in an Array
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }

        if (nums.length == 1) {
            return Collections.EMPTY_LIST;
        }

        int n = nums.length;
        Boolean[] flags = new Boolean[n];
        for (int i = 0; i < n; i++) {
            flags[i] = false;
        }

        for (int i = 0; i < nums.length; i++) {
            flags[nums[i] - 1] = true;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (flags[i] == false) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
     * <p>
     * The basic idea is that we iterate through the input array and mark elements
     * as negative using nums[nums[i] -1] = -nums[nums[i]-1].
     * In this way all the numbers that we have seen will be marked as negative.
     * In the second iteration, if a value is not marked as negative,
     * it implies we have never seen that index before, so just add it to the return list.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        _0448_Find_All_Numbers_Disappeared_in_an_Array main = new _0448_Find_All_Numbers_Disappeared_in_an_Array();
        System.out.println(main.findDisappearedNumbers2(new int[]{2, 4, 1, 1}));
    }

}

