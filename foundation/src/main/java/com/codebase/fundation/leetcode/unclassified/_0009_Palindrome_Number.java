package com.codebase.fundation.leetcode.unclassified;

public class _0009_Palindrome_Number {

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        long i = 0;
        int k = x;
        while (k > 0) {
            i = i * 10 + k % 10;
            k = k / 10;
        }
        if (x > Integer.MAX_VALUE) {
            return false;
        }

        return (x == i);
    }

    public static void main(String[] args) {
        _0009_Palindrome_Number main = new _0009_Palindrome_Number();
        System.out.println(main.isPalindrome(-1));
        System.out.println(main.isPalindrome(0));
        System.out.println(main.isPalindrome(232));
        System.out.println(main.isPalindrome(2332));
        System.out.println(main.isPalindrome(2232));
    }

}
