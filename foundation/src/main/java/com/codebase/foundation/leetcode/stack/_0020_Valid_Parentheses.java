package com.codebase.foundation.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _0020_Valid_Parentheses {

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>(4);
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        int removedSize = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = stack.pop();
                if (c != map.get(ch).charValue()) {
                    return false;
                }
                removedSize += 2;
            } else {
                stack.push(ch);
                if (stack.size() > (length - removedSize) / 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _0020_Valid_Parentheses main = new _0020_Valid_Parentheses();
        System.out.println(main.isValid("(]"));
        System.out.println(main.isValid("(][)"));
        System.out.println(main.isValid("([])"));
        System.out.println(main.isValid("[]()"));
        System.out.println(main.isValid("[({})]"));
        System.out.println(main.isValid("[({}])"));
        System.out.println(main.isValid("(("));
    }
}
