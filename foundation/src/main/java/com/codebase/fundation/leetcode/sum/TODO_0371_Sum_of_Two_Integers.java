package com.codebase.fundation.leetcode.sum;

public class TODO_0371_Sum_of_Two_Integers {

    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        //TODO
        return 0;
    }

    public static void main(String[] args) {
        TODO_0371_Sum_of_Two_Integers main = new TODO_0371_Sum_of_Two_Integers();
        System.out.println(main.getSum(1, 2));//3
        System.out.println(main.getSum(Integer.MIN_VALUE + 1, -2));//0
        System.out.println(main.getSum(Integer.MIN_VALUE, -1));//0
        System.out.println(main.getSum(Integer.MAX_VALUE, 1));//0
        System.out.println(main.getSum(Integer.MAX_VALUE - 1, 2));//0
        System.out.println(main.getSum(-1, 2));//1
    }

}
