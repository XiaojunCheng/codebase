package com.codebase.fundation.leetcode.unclassified;

/**
 * 需要注意细节
 */
public class _0006_ZigZag_Conversion {

    public String convert(String s, int numRows) {
        int length = s.length();
        if (numRows == 1 || length <= numRows) {
            return s;
        }

        char[] convertChars = new char[length];
        int index = 0;
        //第一行
        int size = 1 + (length - 1) / (2 * numRows - 2);
        for (int i = 0; i < size; i++) {
            convertChars[index++] = s.charAt((2 * numRows - 2) * i);
        }
        for (int i = 1; i < numRows - 1; i++) {
            convertChars[index++] = s.charAt(i);
            for (int j = 1; j <= size; j++) {
                if (j == size - 1) {
                    convertChars[index++] = s.charAt((2 * numRows - 2) * (size - 1) - i);
                    int lastIndex = (2 * numRows - 2) * j + i;
                    if (lastIndex < length) {
                        convertChars[index++] = s.charAt(lastIndex);
                    }
                    continue;
                }
                if (j == size) {
                    int startIndex = (2 * numRows - 2) * j - i;
                    if (startIndex < length) {
                        convertChars[index++] = s.charAt(startIndex);
                    }
                    continue;
                }
                convertChars[index++] = s.charAt((2 * numRows - 2) * j - i);
                convertChars[index++] = s.charAt((2 * numRows - 2) * j + i);
            }
        }

        for (int i = 0; i < length - index; i++) {
            convertChars[index + i] = s.charAt((2 * numRows - 2) * i + numRows - 1);
        }
        return new String(convertChars);
    }

    public static void main(String[] args) {
        _0006_ZigZag_Conversion main = new _0006_ZigZag_Conversion();
        System.out.println(main.convert("PAYPALISHIRING", 3));
        System.out.println(main.convert("ABCD", 3));
        System.out.println(main.convert("ABCD", 2));
    }
}
