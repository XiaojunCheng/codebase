package com.codebase.foundation.leetcode.math;

public class _0367_Valid_Perfect_Square {

    public static boolean isPerfectSquare(int num) {
        if (num <= 1) {
            return true;
        }

        int sqrt = _0069_Sqrt_x.mySqrt(num);
        return sqrt * sqrt == num;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 20; i++) {
            System.out.println(i + "-->" + _0367_Valid_Perfect_Square.isPerfectSquare(i));
        }
    }
}
