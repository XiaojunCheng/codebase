package com.codebase.foundation.leetcode.bitoperate;

/**
 * Runtime: 4 ms, faster than 100.00% of Java online submissions for Hamming Distance.
 * Memory Usage: 37.1 MB, less than 6.13% of Java online submissions for Hamming Distance.
 */
public class _0461_Hamming_Distance {

    public int hammingDistance(int x, int y) {
        x ^= y;
        int count = 0;
        while (x != 0) {
            count++;
            x = x & (x - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        _0461_Hamming_Distance main = new _0461_Hamming_Distance();
        System.out.println(main.hammingDistance(1, 4));
        System.out.println(main.hammingDistance(1, 8));
        System.out.println(main.hammingDistance(1, 12));
    }

}
