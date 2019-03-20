package com.codebase.foundation.leetcode.bitoperate;

/**
 * @author Xiaojun.Cheng
 * @date 2019/3/19
 */
public class BitUtil {

    /**
     * 是否为奇数
     *
     * @param n
     * @return
     */
    public static boolean isOdd(int n) {
        return (n & 1) != 0;
    }

    /**
     * 是否为偶数
     *
     * @param n
     * @return
     */
    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    /**
     * 是否2的n次方
     *
     * @param n
     * @return
     */
    public static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static int count1OfPower2(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(isOdd(-1));
        System.out.println(isOdd(-2));
        System.out.println(isOdd(1));
        System.out.println(isOdd(2));
        System.out.println(isEven(-1));
        System.out.println(isEven(-2));
        System.out.println(isEven(1));
        System.out.println(isEven(2));

        /**
         *
         */
        System.out.println(isPowerOf2(0));
        System.out.println(isPowerOf2(3));
        System.out.println(isPowerOf2(4));
        System.out.println(Integer.toHexString(-1));
    }

}
