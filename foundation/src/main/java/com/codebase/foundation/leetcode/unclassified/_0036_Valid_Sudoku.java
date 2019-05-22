package com.codebase.foundation.leetcode.unclassified;

import java.util.HashSet;
import java.util.Set;

public class _0036_Valid_Sudoku {

    public boolean isValidSudoku(char[][] board) {

        for (int row = 0; row < 9; row++) {
            Set<Character> charSet = new HashSet<>();
            for (int column = 0; column < 9; column++) {
                char ch = board[row][column];
                if (ch == '.') {
                    continue;
                }
                if (charSet.contains(ch)) {
                    return false;
                }
                charSet.add(ch);
            }
        }

        for (int column = 0; column < 9; column++) {
            Set<Character> charSet = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                char ch = board[row][column];
                if (ch == '.') {
                    continue;
                }
                if (charSet.contains(ch)) {
                    return false;
                }
                charSet.add(ch);
            }
        }

        for (int i = 0; i < 9; i++) {
            Set<Character> charSet = new HashSet<>();
            for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
                for (int column = (i % 3) * 3; column < (i % 3) * 3 + 3; column++) {
                    char ch = board[row][column];
                    if (ch == '.') {
                        continue;
                    }
                    if (charSet.contains(ch)) {
                        return false;
                    }
                    charSet.add(ch);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        _0036_Valid_Sudoku main = new _0036_Valid_Sudoku();
//        System.out.println(main.isValidSudoku(new char[][]{//
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},//0
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},//1
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},//2
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},//3
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},//4
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},//5
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},//6
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},//7
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}//8
//        }));
        System.out.println(main.isValidSudoku(new char[][]{//
                "....5..1.".toCharArray(),//
                ".4.3.....".toCharArray(),//
                ".....3..1".toCharArray(),//
                "8......2.".toCharArray(),//
                "..2.7....".toCharArray(),//
                ".15......".toCharArray(),//
                ".....2...".toCharArray(),//
                ".2.9.....".toCharArray(),//
                "..4......".toCharArray()//
        }));
    }
}
