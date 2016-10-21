package com.codebase.fundation.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0013_Roman_to_Integer {

    public int romanToInt(String s) {

        Map<Character, Integer> romanToInts = new HashMap<>();
        romanToInts.put('I', 1);
        romanToInts.put('V', 5);
        romanToInts.put('X', 10);
        romanToInts.put('L', 50);
        romanToInts.put('C', 100);
        romanToInts.put('D', 500);
        romanToInts.put('M', 1000);

        int length = s.length();
        int value = 0;
        int tmpValue = romanToInts.get(s.charAt(0));
        int tmpTotalValue = tmpValue;
        for (int i = 1; i < length; i++) {
            int chValue = romanToInts.get(s.charAt(i));
            if (tmpValue > chValue) {
                value += tmpTotalValue;
                tmpValue = chValue;
                tmpTotalValue = tmpValue;
            } else if (tmpValue == chValue) {
                tmpTotalValue += chValue;
            } else {
                value -= tmpTotalValue;
                tmpValue = chValue;
                tmpTotalValue = tmpValue;
            }
        }
        return value + tmpTotalValue;
    }

    public static void main(String[] args) {
        _0013_Roman_to_Integer main = new _0013_Roman_to_Integer();
        System.out.println(main.romanToInt("I"));//1
        System.out.println(main.romanToInt("IIIX"));//7
        System.out.println(main.romanToInt("DCXXI"));//621
        System.out.println(main.romanToInt("MCMLXXX"));//1980
        System.out.println(main.romanToInt("MMMIM"));//3999
        System.out.println(main.romanToInt("MMMDLXXXVI"));//3999
    }
}
