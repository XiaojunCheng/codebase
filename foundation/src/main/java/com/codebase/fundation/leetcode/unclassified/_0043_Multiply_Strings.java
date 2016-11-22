package com.codebase.fundation.leetcode.unclassified;

import java.util.HashMap;
import java.util.Map;

public class _0043_Multiply_Strings {

    public String multiply(String num1, String num2) {

        if (num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()) {
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 >= len2) {
            return doMultiply(num1, num2);
        } else {
            return doMultiply(num2, num1);
        }
    }

    public String doMultiply(String num1, String num2) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);

        StringBuilder reverseNum1Builder = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            reverseNum1Builder.append(num1.charAt(i));
        }
        String reverseNum1 = reverseNum1Builder.toString();
        StringBuilder reverseNum2Builder = new StringBuilder();
        for (int i = num2.length() - 1; i >= 0; i--) {
            reverseNum2Builder.append(num2.charAt(i));
        }
        String reverseNum2 = reverseNum2Builder.toString();

        int[] results = new int[num1.length() + num2.length() + 1];
        for (int indexI = 0; indexI < reverseNum1.length(); indexI++) {
            char charOne = reverseNum1.charAt(indexI);
            if (charOne == '0') {
                continue;
            }

            int valueOne = map.get(charOne);
            int addFactor = 0;
            for (int indexJ = 0; indexJ < reverseNum2.length(); indexJ++) {
                char charTwo = reverseNum2.charAt(indexJ);
                int valueTwo = map.get(charTwo);
                int add = valueOne * valueTwo + addFactor + results[indexI + indexJ];
                if (add < 10) {
                    addFactor = 0;
                } else {
                    addFactor = add / 10;
                    add = add % 10;
                }
                results[indexI + indexJ] = add;
            }

            int length = num2.length();
            while (addFactor >= 1) {
                results[indexI + length] += addFactor;
                if (results[indexI + length] < 10) {
                    addFactor = 0;
                } else {
                    addFactor = results[indexI + length] / 10;
                    results[indexI + length] = results[indexI + length] % 10;
                }
            }
        }

        StringBuilder builder = new StringBuilder(results.length);
        boolean found = false;
        for (int i = results.length - 1; i >= 0; i--) {
            int value = results[i];
            if (found == false && value == 0) {
                continue;
            }
            found = true;
            builder.append(value);
        }

        if (found) {
            return builder.toString();
        } else {
            return "0";
        }
    }

    public static void main(String[] args) {
        _0043_Multiply_Strings main = new _0043_Multiply_Strings();
        //System.out.println(main.multiply("", "a"));//0
        System.out.println(main.multiply("12", "12"));//144
        System.out.println(main.multiply("123", "1234"));//151782
        System.out.println(main.multiply("9", "9"));//81
        System.out.println(main.multiply("9", "99"));//891

    }
}
