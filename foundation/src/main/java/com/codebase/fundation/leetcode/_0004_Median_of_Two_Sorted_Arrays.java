package com.codebase.fundation.leetcode;

public class _0004_Median_of_Two_Sorted_Arrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int totalCount = nums1.length + nums2.length;
        int midPos = (totalCount + 1) / 2;
        if (nums1.length == 0) {
            return findMedian(midPos, nums2);
        }

        if (nums2.length == 0) {
            return findMedian(midPos, nums1);
        }

        if (nums1[nums1.length - 1] <= nums2[0]) {//nums1 < nums2
            return findMedian(midPos, nums1.length + 1 - midPos, nums1, nums2);
        }

        if (nums2[nums1.length - 1] <= nums1[0]) {//nums2 < nums1
            return findMedian(midPos, nums1.length + 1 - midPos, nums2, nums1);
        }

        if (totalCount % 2 == 0) {
            return processTwoMedian(midPos, nums1, nums2);
        } else {
            return processOneMedian(midPos, nums1, nums2);
        }
    }

    private static double findMedian(int midPos, int midPos2, int[] smallerNums, int[] biggerNums) {
        int num1 = (midPos <= smallerNums.length) ? smallerNums[midPos - 1] : biggerNums[midPos - smallerNums.length - 1];
        int num2 = (midPos2 <= smallerNums.length) ? smallerNums[midPos2 + 1 - 1] : biggerNums[midPos2 + 1 - smallerNums.length - 1];
        return (num1 + num2) / 2.0;
    }

    private static double findMedian(int midPos, int[] nums) {
        return (double) (nums[midPos - 1] + nums[nums.length - midPos]) / 2.0;
    }

    private static double processOneMedian(int midPos, int[] nums1, int[] nums2) {

        if (nums1[nums1.length - 1] <= nums2[0]) {//nums1, nums2
            if (midPos <= nums1.length) {
                return nums1[midPos - 1] / 1.0;
            } else {
                return nums2[midPos - nums1.length - 1] / 1.0;
            }
        }

        if (nums2[nums2.length - 1] <= nums1[0]) {//nums2, nums1
            if (midPos <= nums2.length) {
                return nums2[midPos - 1] / 1.0;
            } else {
                return nums1[midPos - nums2.length - 1] / 1.0;
            }
        }

        int nextPos1 = 0;
        int nextPos2 = 0;
        int curValue;
        int count = 0;
        while (true) {
            if (nextPos1 >= nums1.length) {
                return nums2[midPos - nums1.length - 1] / 1.0;
            }

            if (nextPos2 >= nums2.length) {
                return nums1[midPos - nums2.length - 1] / 1.0;
            }

            if (nums1[nextPos1] <= nums2[nextPos2]) {
                curValue = nums1[nextPos1];
                nextPos1++;
            } else {
                curValue = nums2[nextPos2];
                nextPos2++;
            }
            count++;
            if (count == midPos) {
                return curValue / 1.0;
            }
        }
    }

    private static double processTwoMedian(int midPos, int[] nums1, int[] nums2) {

        if (nums1[nums1.length - 1] <= nums2[0]) {//nums1, nums2
            int num1 = (midPos <= nums1.length) ? nums1[midPos - 1] : nums2[midPos - nums1.length - 1];
            int num2 = (midPos + 1 <= nums1.length) ? nums1[midPos + 1 - 1] : nums2[midPos + 1 - nums1.length - 1];
            return (num1 + num2) / 2.0;
        }

        if (nums2[nums2.length - 1] <= nums1[0]) {//nums2, nums1
            int num1 = (midPos <= nums2.length) ? nums2[midPos - 1] : nums1[midPos - nums2.length - 1];
            int num2 = (midPos + 1 <= nums2.length) ? nums2[midPos + 1 - 1] : nums1[midPos + 1 - nums2.length - 1];
            return (num1 + num2) / 2.0;
        }

        int nextPos1 = 0;
        int nextPos2 = 0;
        boolean foundOne = false;
        int oneValue = 0;
        int curValue;
        int count = 0;
        while (true) {
            if (nextPos1 >= nums1.length) {
                if (foundOne) {
                    return (oneValue + nums2[midPos - nums1.length]) / 2.0;
                } else {
                    return (nums2[midPos - nums1.length - 1] + nums2[midPos - nums1.length]) / 2.0;
                }
            }

            if (nextPos2 >= nums2.length) {
                if (foundOne) {
                    return (oneValue + nums1[midPos - nums2.length]) / 2.0;
                } else {
                    return (nums1[midPos - nums2.length - 1] + nums1[midPos - nums2.length]) / 2.0;
                }
            }

            if (nums1[nextPos1] <= nums2[nextPos2]) {
                curValue = nums1[nextPos1];
                nextPos1++;
            } else {
                curValue = nums2[nextPos2];
                nextPos2++;
            }
            count++;
            if (count == midPos) {
                oneValue = curValue;
                foundOne = true;
            } else if (count == midPos + 1) {
                return (oneValue + curValue) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        //        //1. 空
        //        System.out.println(findMedianSortedArrays(new int[] {2}, new int[] {}));
        //        System.out.println(findMedianSortedArrays(new int[] {3, 4}, new int[] {}));
        //        System.out.println(findMedianSortedArrays(new int[] {}, new int[] {2}));
        //        System.out.println(findMedianSortedArrays(new int[] {}, new int[] {3, 4}));
        //        //2. 奇数测试
        //        System.out.println(findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        //        //3. 偶数测试
        //        System.out.println(findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
        System.out.println(findMedianSortedArrays(new int[] {2}, new int[] {1, 3, 4}));
    }
}
