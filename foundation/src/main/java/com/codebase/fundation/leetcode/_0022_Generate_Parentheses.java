package com.codebase.fundation.leetcode;

import java.util.*;

public class _0022_Generate_Parentheses {


    public List<String> generateParenthesis(int n) {

        List<String> solutions = new ArrayList<>();

        if (n == 0) {
            solutions.add("");
            return solutions;
        }

        if (n == 1) {
            solutions.add("()");
            return solutions;
        }

        Map<Integer, Set<String>> solutionsMap = new HashMap<>();
        {
            Set<String> oneSolutions = new HashSet<>();
            oneSolutions.add("()");
            solutionsMap.put(1, oneSolutions);
        }

        int k = 2;
        while (!solutionsMap.containsKey(n)) {
            Set<String> kSolutions = new HashSet<>();
            Set<String> k_1Solutions = solutionsMap.get(k - 1);
            for (String s : k_1Solutions) {
                kSolutions.add("(" + s + ")");
            }

            for (int i = 1; i <= (k + 1) / 2; i++) {
                Set<String> leftSet = solutionsMap.get(i);
                Set<String> rightSet = solutionsMap.get(k - i);
                for (String left : leftSet) {
                    for (String right : rightSet) {
                        kSolutions.add(left + right);
                        kSolutions.add(right + left);
                    }
                }
            }
            solutionsMap.put(k, kSolutions);
            k++;
        }
        solutions.addAll(solutionsMap.get(n));
        return solutions;
    }

    public static void main(String[] args) {
        _0022_Generate_Parentheses main = new _0022_Generate_Parentheses();

        for (int i = 3; i < 4; i++) {
            System.out.println("============= ");
            List<String> solutions = main.generateParenthesis(i);
            for (String s : solutions) {
                System.out.println(s);
            }
        }
    }

}
