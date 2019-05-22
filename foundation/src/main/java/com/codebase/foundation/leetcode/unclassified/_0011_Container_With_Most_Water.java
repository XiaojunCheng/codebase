package com.codebase.foundation.leetcode.unclassified;

import com.codebase.foundation.leetcode.TreeNode;
import jnr.ffi.annotations.In;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _0011_Container_With_Most_Water {

    /**
     * 最直观的方法：双重循环
     * Runtime: 202 ms, faster than 26.04% of Java online submissions for Container With Most Water.
     * Memory Usage: 41.1 MB, less than 5.05% of Java online submissions for Container With Most Water.
     */
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }


}
