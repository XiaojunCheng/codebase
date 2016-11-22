package com.codebase.fundation.leetcode.unclassified;

/**
 * 边界条件处理
 */
public class _0007_Reverse_Integer {

    public int reverse(int x) {
        if (x >= 0) {
            long result = reversePositive(x);
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) result;
        } else {
            long result = reversePositive(-x);
            if (result - 1 > Integer.MAX_VALUE) {
                return 0;
            }
            if (result - 1 == Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) -result;
        }
    }

    public long reversePositive(int x) {
        long i = 0;
        while (x > 0) {
            i = i * 10 + x % 10;
            x = x / 10;
        }
        return i;
    }

    public static void main(String[] args) {
        _0007_Reverse_Integer main = new _0007_Reverse_Integer();
        System.out.println(main.reverse(123));
        System.out.println(main.reverse(-123));
        System.out.println(main.reverse(1534236469));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
