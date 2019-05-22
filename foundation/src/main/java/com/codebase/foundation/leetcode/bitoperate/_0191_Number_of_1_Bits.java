package com.codebase.foundation.leetcode.bitoperate;

public class _0191_Number_of_1_Bits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        _0191_Number_of_1_Bits main = new _0191_Number_of_1_Bits();
        System.out.println(main.hammingWeight(-3));
    }
}
