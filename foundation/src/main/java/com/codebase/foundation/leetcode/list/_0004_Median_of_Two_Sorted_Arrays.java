package com.codebase.foundation.leetcode.list;

/**
 * Runtime: 3 ms, faster than 100.00% of Java online submissions for Median of Two Sorted Arrays.
 * Memory Usage: 47.8 MB, less than 90.79% of Java online submissions for Median of Two Sorted Arrays.
 */
public class _0004_Median_of_Two_Sorted_Arrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0d;
        }

        if (nums1.length == 0) {
            return calMedian(nums2);
        }

        if (nums2.length == 0) {
            return calMedian(nums1);
        }

        return ((nums1.length + nums2.length) % 2 == 0) ?
                findMedianSortedArrays4Two(nums1, nums2) :
                findMedianSortedArrays4One(nums1, nums2);
    }

    private double findMedianSortedArrays4One(int[] nums1, int[] nums2) {
        int midIndex = (nums1.length + nums2.length + 1) / 2;

        int count = 0;
        int indexOfNums1 = 0;
        int indexOfNums2 = 0;
        while (indexOfNums1 < nums1.length && indexOfNums2 < nums2.length) {
            count++;
            if (count == midIndex) {
                int curValue = (nums1[indexOfNums1] <= nums2[indexOfNums2]) ?
                        nums1[indexOfNums1] :
                        nums2[indexOfNums2];
                return Double.sum(0, curValue);
            }

            if (nums1[indexOfNums1] <= nums2[indexOfNums2]) {
                indexOfNums1++;
            } else {
                indexOfNums2++;
            }
        }

        return (indexOfNums1 == nums1.length) ? nums2[indexOfNums2 - 1 + midIndex - count] :
                nums1[indexOfNums1 - 1 + midIndex - count];
    }

    private double findMedianSortedArrays4Two(int[] nums1, int[] nums2) {
        int midIndex = (nums1.length + nums2.length + 1) / 2;

        int count = 0;
        int indexOfNums1 = 0;
        int indexOfNums2 = 0;
        int firstOne = 0;
        while (indexOfNums1 < nums1.length && indexOfNums2 < nums2.length) {
            count++;
            if (count == midIndex) {
                firstOne = (nums1[indexOfNums1] <= nums2[indexOfNums2]) ?
                        nums1[indexOfNums1] :
                        nums2[indexOfNums2];
            }

            if (count == midIndex + 1) {
                int cur = (nums1[indexOfNums1] <= nums2[indexOfNums2]) ?
                        nums1[indexOfNums1] :
                        nums2[indexOfNums2];
                return Double.sum(firstOne, cur) / 2;
            }

            if (nums1[indexOfNums1] <= nums2[indexOfNums2]) {
                indexOfNums1++;
            } else {
                indexOfNums2++;
            }
        }

        if (midIndex == count) {
            return Double.sum(firstOne, (indexOfNums1 == nums1.length) ? nums2[indexOfNums2] : nums1[indexOfNums1]) / 2;
        }

        return (indexOfNums1 == nums1.length) ? Double.sum(nums2[indexOfNums2 - 1 + midIndex - count], nums2[indexOfNums2 + midIndex - count]) / 2 :
                Double.sum(nums1[indexOfNums1 - 1 + midIndex - count], nums1[indexOfNums1 + midIndex - count]) / 2;
    }

    private double calMedian(int[] array) {
        if (array.length % 2 == 0) {
            return Double.sum(array[array.length / 2 - 1], array[array.length / 2]) / 2;
        } else {
            return Double.sum(0, array[(array.length - 1) / 2]);
        }
    }


    public static void main(String[] args) {
        _0004_Median_of_Two_Sorted_Arrays main = new _0004_Median_of_Two_Sorted_Arrays();
        System.out.println(main.findMedianSortedArrays(new int[]{1}, new int[]{}));
        System.out.println(main.findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println(main.findMedianSortedArrays(new int[]{1}, new int[]{1}));
        System.out.println(main.findMedianSortedArrays(new int[]{1}, new int[]{2}));
        System.out.println(main.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(main.findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}));
        System.out.println(main.findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4}));
    }

}
