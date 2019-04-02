package com.codebase.foundation.leetcode.bitoperate;

public class _0338_Counting_Bits {

    /**
     * Runtime: 1 ms, faster than 99.94% of Java online submissions for Counting Bits.
     * Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Counting Bits.
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }

        if (num == 1) {
            return new int[]{0, 1};
        }

        int[] ret = new int[num + 1];
        ret[0] = 0;
        ret[1] = 1;

        int flag = 1;
        int nextFlag = 2;
        for (int i = 2; i <= num; i++) {
            if (i == nextFlag) {
                flag = nextFlag;
                nextFlag = nextFlag << 1;
            }
            ret[i] = 1 + ret[i - flag];
        }
        return ret;
    }

    public static void main(String[] args) {
        _0338_Counting_Bits main = new _0338_Counting_Bits();
        System.out.println(main.countBits(7));
    }

}
