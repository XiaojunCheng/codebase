package com.codebase.fundation.leetcode.cases;

public class _0008_String_to_Integer_atoi {

    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int length = str.length();
        char valueOf0 = '0';
        char valueOf9 = '9';

        int startIndex = 0;
        int endIndex = length - 1;
        while (startIndex <= endIndex && str.charAt(startIndex) == ' ') {
            startIndex++;
        }
        while (endIndex > startIndex && str.charAt(endIndex) == ' ') {
            endIndex--;
        }

        boolean flag = (str.charAt(startIndex) == '-') ? true : false;
        startIndex = flag ? startIndex + 1 : startIndex;
        startIndex = (!flag && str.charAt(startIndex) == '+') ? startIndex + 1 : startIndex;

        boolean hasNum = false;
        long result = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            char ch = str.charAt(i);
            if (ch < valueOf0 || ch > valueOf9) {
                if (hasNum) {
                    break;
                } else {
                    return 0;
                }
            }

            hasNum = true;
            result = result * 10 + (ch - valueOf0);
            if (result - 1 > Integer.MAX_VALUE) {
                break;
            }
        }

        if (flag) {
            if (result - 1 >= Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) -result;
        } else {
            if (result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) result;
        }
    }

    public static void main(String[] args) {
        _0008_String_to_Integer_atoi main = new _0008_String_to_Integer_atoi();
        System.out.println(main.myAtoi("2147483648"));//
        System.out.println(main.myAtoi("-2147483648"));//
        System.out.println(main.myAtoi("     -2147483648"));//
        System.out.println(main.myAtoi("-2147483648     "));//
        System.out.println(main.myAtoi("     -2147483648     "));//
        System.out.println(main.myAtoi("2147483647"));//
        System.out.println(main.myAtoi("+2147483647"));//
        System.out.println(main.myAtoi("0000000000123456789"));//
        System.out.println(main.myAtoi("-0000000000123456789"));//
        System.out.println(main.myAtoi("-9999999"));
        System.out.println(main.myAtoi("-999b999"));
        System.out.println(main.myAtoi("-b999"));
        System.out.println(main.myAtoi("9999999"));
        System.out.println(main.myAtoi("9999a99"));
        System.out.println(main.myAtoi("99999999999"));//超长
    }
}
