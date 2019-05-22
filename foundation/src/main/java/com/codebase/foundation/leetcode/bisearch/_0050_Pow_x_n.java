package com.codebase.foundation.leetcode.bisearch;

public class _0050_Pow_x_n {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == Integer.MIN_VALUE) {
            return 1 / x * myPow(x, n + 1);
        }

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

}
