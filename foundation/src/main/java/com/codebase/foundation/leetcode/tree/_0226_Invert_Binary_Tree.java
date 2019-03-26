package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
 * Memory Usage: 35.6 MB, less than 66.40% of Java online submissions for Invert Binary Tree.
 */
public class _0226_Invert_Binary_Tree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        if (root.left == null) {
            root.left = invertTree(root.right);
            root.right = null;
            return root;
        }

        if (root.right == null) {
            root.right = invertTree(root.left);
            root.left = null;
            return root;
        }

        TreeNode t = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = t;
        return root;
    }

    public static void main(String[] args) {
        _0226_Invert_Binary_Tree main = new _0226_Invert_Binary_Tree();
        TreeNode.print(main.invertTree(TreeNode.buildNode(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
        TreeNode.print(main.invertTree(TreeNode.buildNode(new Integer[]{1, 2, null})));
    }

}
