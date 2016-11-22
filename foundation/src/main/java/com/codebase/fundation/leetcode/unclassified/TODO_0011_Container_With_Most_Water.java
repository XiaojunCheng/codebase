package com.codebase.fundation.leetcode.unclassified;

public class TODO_0011_Container_With_Most_Water {

    public int maxArea(int[] height) {
        if (height.length <= 1) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        TODO_0011_Container_With_Most_Water main = new TODO_0011_Container_With_Most_Water();
        System.out.println(main.maxArea(new int[]{}));//
    }

}
