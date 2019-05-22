package com.codebase.foundation.leetcode.unclassified;

public class _0048_Rotate_Image {

    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }

        int[][] copyMatrix = new int[matrix.length][];
        for (int i = 0; i < copyMatrix.length; i++) {
            copyMatrix[i] = new int[matrix.length];
        }

        for (int column = 0; column < matrix.length; column++) {
            for (int row = 0; row < matrix.length; row++) {
                copyMatrix[column][matrix.length - 1 - row] = matrix[row][column];
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                matrix[row][column] = copyMatrix[row][column];
            }
        }
    }

    public static void main(String[] args) {
        _0048_Rotate_Image main = new _0048_Rotate_Image();
        int[][] matrix = {//
                {1, 2, 3, 4},//
                {5, 6, 7, 8},//
                {9, 10, 11, 12},//
                {13, 14, 15, 16}//
        };
        main.rotate(matrix);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                System.out.print(matrix[row][column] + ",");
            }
            System.out.println();
        }
    }
}
