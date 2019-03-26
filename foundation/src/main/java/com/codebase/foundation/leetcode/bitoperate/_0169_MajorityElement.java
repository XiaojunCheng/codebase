package com.codebase.foundation.leetcode.bitoperate;

/**
 * Runtime: 2 ms, faster than 100.00% of Java online submissions for Majority Element.
 * Memory Usage: 41.3 MB, less than 28.54% of Java online submissions for Majority Element.
 */
public class _0169_MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums[0];
        }

        int majorityEle = 0;
        for (int moveLeftSize = 0; moveLeftSize < 32; moveLeftSize++) {
            int flag = (moveLeftSize == 0) ? 1 : 1 << moveLeftSize;
            int bitOneCount = 0;
            for (int index = 0; index < nums.length; index++) {
                if ((nums[index] & flag) == flag) {
                    bitOneCount++;
                }
            }
            if (bitOneCount > (nums.length) / 2) {
                majorityEle |= flag;
            }
        }

        return majorityEle;
    }

    public static void main(String[] args) {
        _0169_MajorityElement main = new _0169_MajorityElement();
        System.out.println(main.majorityElement(new int[]{3}));
        System.out.println(main.majorityElement(new int[]{2, 2}));
        System.out.println(main.majorityElement(new int[]{3, 2, 3}));
        System.out.println(main.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

}
