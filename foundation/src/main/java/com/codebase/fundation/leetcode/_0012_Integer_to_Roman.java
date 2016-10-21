package com.codebase.fundation.leetcode;

/**
 * Created by chengxiaojun on 16/10/20.
 */
public class _0012_Integer_to_Roman {

    public String intToRoman(int num) {

        int[] romanValues = {1000, 500, 100, 50, 10, 5, 1};
        char[] romanChars = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] combineValues = {900, 400, 90, 40, 9, 4};
        int[] romanCharsIndex = {2, 1, 2, 1, 2, 1};

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < romanValues.length - 1; i++) {
            while (num >= romanValues[i]) {
                num -= romanValues[i];
                builder.append(romanChars[i]);
            }

            if (num >= combineValues[i]) {
                num = num - romanValues[i] + romanValues[i + romanCharsIndex[i]];
                builder.append(romanChars[i + romanCharsIndex[i]]);
                builder.append(romanChars[i]);
            }
        }

        while (num >= romanValues[romanValues.length - 1]) {
            num -= romanValues[romanValues.length - 1];
            builder.append(romanChars[romanValues.length - 1]);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        _0012_Integer_to_Roman main = new _0012_Integer_to_Roman();
        System.out.println(main.intToRoman(1));//I
        System.out.println(main.intToRoman(7));//XII
        System.out.println(main.intToRoman(621));//DCXXI
        System.out.println(main.intToRoman(1980));//MCMLXXX
        System.out.println(main.intToRoman(3999));//MMMCMXCIX
    }
}
