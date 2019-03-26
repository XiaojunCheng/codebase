package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

/**
 * Runtime: 20 ms, faster than 5.86% of Java online submissions for Diameter of Binary Tree.
 * Memory Usage: 41.8 MB, less than 5.05% of Java online submissions for Diameter of Binary Tree.
 */
public class _0543_Diameter_of_Binary_Tree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return diameterOfBinaryTree(root, 0);
    }

    private int diameterOfBinaryTree(TreeNode root, int level) {
        if (root.left == null && root.right == null) {
            return level;
        }

        if (root.left == null) {
            return Math.max(level + highOfBinaryTree(root.right), diameterOfBinaryTree(root.right));
        }

        if (root.right == null) {
            return Math.max(level + highOfBinaryTree(root.left), diameterOfBinaryTree(root.left));
        }

        int leftDiameter = diameterOfBinaryTree(root.left, level + 1);
        int rightDiameter = diameterOfBinaryTree(root.right, level + 1);
        int leftHeight = highOfBinaryTree(root.left);
        int rightHeight = highOfBinaryTree(root.right);

        return Math.max(leftDiameter, Math.max(rightDiameter, Math.max(leftHeight + rightHeight, Math.max(leftHeight + level, rightHeight + level))));
    }

    private int highOfBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null) {
            return 1 + highOfBinaryTree(root.right);
        }

        if (root.right == null) {
            return 1 + highOfBinaryTree(root.left);
        }
        return 1 + Math.max(highOfBinaryTree(root.left), highOfBinaryTree(root.right));
    }

}
