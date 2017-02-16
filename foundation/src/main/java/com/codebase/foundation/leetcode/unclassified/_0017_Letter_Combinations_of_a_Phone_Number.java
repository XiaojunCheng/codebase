package com.codebase.foundation.leetcode.unclassified;

import java.util.ArrayList;
import java.util.List;

public class _0017_Letter_Combinations_of_a_Phone_Number {

    public List<String> letterCombinations(String digits) {
        List<String> solutions = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return solutions;
        }

        char[][] dict = {//
                {'a', 'b', 'c'},//
                {'d', 'e', 'f'},//
                {'g', 'h', 'i'},//
                {'j', 'k', 'l'},//
                {'m', 'n', 'o'},//
                {'p', 'q', 'r', 's'},//
                {'t', 'u', 'v'},//
                {'w', 'x', 'y', 'z'}//
        };

        List<StringBuilder> candidates = new ArrayList<>();
        for (int digitIndex = 0; digitIndex < digits.length(); digitIndex++) {
            char ch = digits.charAt(digitIndex);
            if (ch < '2' || ch > '9') {
                continue;
            }

            char[] letters = dict[ch - '2'];
            if (candidates.isEmpty()) {
                for (int letterIndex = 0; letterIndex < letters.length; letterIndex++) {
                    StringBuilder builder = new StringBuilder(digits.length());
                    builder.append(letters[letterIndex]);
                    candidates.add(builder);
                }
                continue;
            }

            for (int i = candidates.size() - 1; i >= 0; i--) {
                StringBuilder builder = candidates.get(i);
                for (int letterIndex = 1; letterIndex < letters.length; letterIndex++) {
                    StringBuilder anotherBuilder = new StringBuilder(digits.length());
                    anotherBuilder.append(builder);
                    anotherBuilder.append(letters[letterIndex]);
                    candidates.add(anotherBuilder);
                }
                builder.append(letters[0]);
            }
        }

        for (StringBuilder builder : candidates) {
            solutions.add(builder.toString());
        }

        return solutions;
    }

    public static void main(String[] args) {
        _0017_Letter_Combinations_of_a_Phone_Number main = new _0017_Letter_Combinations_of_a_Phone_Number();
        List<String> solutions = main.letterCombinations("234");
        for (String s : solutions) {
            System.out.println(s);
        }
    }
}
