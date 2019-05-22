package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

/**
 * Runtime: 20 ms, faster than 17.09% of Java online submissions for Path Sum III.
 * Memory Usage: 39.3 MB, less than 81.25% of Java online submissions for Path Sum III.
 */
public class _0437_Path_Sum_III {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSum(root, sum, sum);
    }

    private int pathSum(TreeNode root, int remainSum, int sum) {
        if (root == null) {
            return 0;
        }

        if (root.val == remainSum) {
            return pathSum(root.left, sum, sum) + pathSum(root.right, sum, sum) + 1 + pathSumWithNode(root.left, 0, sum) + pathSumWithNode(root.right, 0, sum);
        }

        if (root.left == null && root.right == null) {
            return (root.val == remainSum) ? 1 : 0;
        }

        if (root.left == null) {
            return pathSumWithNode(root.right, remainSum - root.val, sum) + pathSum(root.right, sum, sum);
        }

        if (root.right == null) {
            return pathSumWithNode(root.left, remainSum - root.val, sum) + pathSum(root.left, sum, sum);
        }

        int i = pathSumWithNode(root.left, remainSum - root.val, sum);
        int j = pathSum(root.left, sum, sum);
        int k = pathSumWithNode(root.right, remainSum - root.val, sum);
        int f = pathSum(root.right, sum, sum);
        return i + j + k + f;
    }

    private int pathSumWithNode(TreeNode root, int remainSum, int sum) {
        if (root == null) {
            return 0;
        }

        if (root.val == remainSum) {
            return 1 + pathSumWithNode(root.left, 0, sum) + pathSumWithNode(root.right, 0, sum);
        }

        return pathSumWithNode(root.left, remainSum - root.val, sum) + pathSumWithNode(root.right, remainSum - root.val, sum);
    }

    public static void main(String[] args) {
        _0437_Path_Sum_III main = new _0437_Path_Sum_III();
        System.out.println(main.pathSum(TreeNode.buildNode(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}), 8));
        System.out.println(main.pathSum(TreeNode.buildNode(new Integer[]{1, -2, -3, 1, 3, -2, null, -1, null, null, null, null, null, null, null}), -1));
        System.out.println(main.pathSum(TreeNode.buildNode(new Integer[]{1, -2, -3, 1, 3, -2, null, -1, null, null, null, null, null, null, null}), -2));
    }

}
