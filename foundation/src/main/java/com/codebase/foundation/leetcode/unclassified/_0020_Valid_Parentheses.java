package com.codebase.foundation.leetcode.unclassified;

import java.util.HashMap;
import java.util.Map;

public class _0020_Valid_Parentheses {

    public boolean isValid(String s) {

        if (s == null || s.isEmpty()) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Map<Character, Integer> count = new HashMap<>();
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            count.put(entry.getKey(), 0);
            count.put(entry.getValue(), 0);
        }

        for (int i = 0; i <= s.length() - 1; i++) {
            char ch = s.charAt(i);
            count.put(ch, count.get(ch) + 1);
        }

        if (!isValid(map, count)) {
            return false;
        }

        return isValid(s, 0, s.length() - 1, map, count);
    }

    private boolean isValid(Map<Character, Character> map, Map<Character, Integer> count) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            char leftChar = entry.getKey();
            char rightChar = entry.getValue();
            if (count.get(leftChar) != count.get(rightChar)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(String s, int start, int end, Map<Character, Character> map, Map<Character, Integer> count) {

        int length = end + 1 - start;
        if (length % 2 != 0) {
            return false;
        }

        if (length == 2) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (map.containsKey(startChar) && map.get(startChar) == endChar) {
                return true;
            } else {
                return false;
            }
        }

        char startChar = s.charAt(start);
        char splitChar = map.get(startChar);
        count.put(startChar, count.get(startChar) + 1);
        int pos = start + 1;
        while (pos <= end) {
            char ch = s.charAt(pos);
            count.put(ch, count.get(ch) + 1);
            if (ch == splitChar && count.get(startChar) == count.get(splitChar)) {
                break;
            }
            pos++;
        }

        if (!isValid(map, count)) {
            return false;
        }

        if (pos == end) {
            while (start < end) {
                char ch = s.charAt(start);
                if (map.containsKey(ch) && (s.charAt(end) == map.get(ch))) {
                    start++;
                    end--;
                }
                break;
            }

            if (start > end) {
                return true;
            } else {
                return isValid(s, start, end, map, count);
            }
        }

        return isValid(s, start, pos, map, count) && isValid(s, pos + 1, end, map, count);
    }

    public static void main(String[] args) {
        _0020_Valid_Parentheses main = new _0020_Valid_Parentheses();
        System.out.println(main.isValid("(]"));
        System.out.println(main.isValid("(][)"));
        System.out.println(main.isValid("([])"));
        System.out.println(main.isValid("[]()"));
        System.out.println(main.isValid("[({})]"));
        System.out.println(main.isValid("[({}])"));
    }
}
