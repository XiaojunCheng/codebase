package com.codebase.foundation.leetcode.unclassified;

public class _0152_Maximum_Product_Subarray {

    /**
     * Runtime: 1 ms, faster than 97.88% of Java online submissions for Maximum Product Subarray.
     * Memory Usage: 37.4 MB, less than 57.66% of Java online submissions for Maximum Product Subarray.
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int curIndex = 0;
        while (curIndex < nums.length) {
            if (nums[curIndex] == 0) {
                curIndex++;
                continue;
            }
            break;
        }

        if (curIndex == nums.length) {
            return 0;
        }

        int maxProduct = (curIndex == 0) ? nums[curIndex] : Math.max(0, nums[curIndex]);
        int maxNegtiveProduct = nums[curIndex] < 0 ? nums[curIndex] : 0;
        int maxPositiveProduct = nums[curIndex] > 0 ? nums[curIndex] : 0;
        int tmp;
        for (int i = curIndex + 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                maxNegtiveProduct = 0;
                maxPositiveProduct = 0;
            } else if (nums[i] > 0) {
                maxPositiveProduct = (maxPositiveProduct > 0) ? maxPositiveProduct * nums[i] : nums[i];
                maxNegtiveProduct = (maxNegtiveProduct == 0) ? 0 : maxNegtiveProduct * nums[i];
            } else {
                tmp = maxPositiveProduct;
                maxPositiveProduct = (maxNegtiveProduct == 0) ? 0 : nums[i] * maxNegtiveProduct;
                maxNegtiveProduct = (tmp == 0) ? nums[i] : tmp * nums[i];
            }

            maxProduct = Math.max(maxProduct, maxPositiveProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        _0152_Maximum_Product_Subarray main = new _0152_Maximum_Product_Subarray();
        System.out.println(main.maxProduct(new int[]{2, 3, -2, 4}));
    }

}
