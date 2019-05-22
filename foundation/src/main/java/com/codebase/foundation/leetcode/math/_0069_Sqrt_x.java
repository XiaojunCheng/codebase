package com.codebase.foundation.leetcode.math;

public class _0069_Sqrt_x {

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Sqrt(x).
     * Memory Usage: 32.4 MB, less than 100.00% of Java online submissions for Sqrt(x).
     * <p>
     * 这里的坑主要是一些边界场景的处理，比如2个整数相乘可能超过整数的最大表示范围
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }

        int start = 1;
        int end = x;
        int mid = (end - start) / 2 + start;

        while (start < end) {
            long product = 1l * mid * mid;
            if (product == x) {
                return mid;
            }

            if (x < product) {
                product = 1l * (mid - 1) * (mid - 1);
                if (x >= product) {
                    return mid - 1;
                }
                end = mid - 1;
            } else {
                product = 1l * (mid + 1) * (mid + 1);
                if (x < product) {
                    return mid;
                }
                start = mid + 1;
            }

            mid = (end - start) / 2 + start;
        }

        if (1l * start * start > x) {
            return start - 1;
        }
        return start;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "->" + _0069_Sqrt_x.mySqrt(i));
        }
        System.out.println(Integer.MAX_VALUE);
        System.out.println(_0069_Sqrt_x.mySqrt(Integer.MAX_VALUE));
    }
}
