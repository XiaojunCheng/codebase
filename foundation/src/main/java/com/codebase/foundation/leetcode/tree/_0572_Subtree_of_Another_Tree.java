package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

/**
 * Runtime: 8 ms, faster than 98.30% of Java online submissions for Subtree of Another Tree.
 * Memory Usage: 40.8 MB, less than 91.79% of Java online submissions for Subtree of Another Tree.
 */
public class _0572_Subtree_of_Another_Tree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == t;
        }

        if (s.val == t.val) {
            if (isStrictSubTree(s.left, t.left)) {
                if (isStrictSubTree(s.right, t.right)) {
                    return true;
                }
            }
        }

        if (isSubtree(s.left, t)) {
            return true;
        }

        return isSubtree(s.right, t);
    }

    private boolean isStrictSubTree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == t;
        }

        if (s.val != t.val) {
            return false;
        }

        if (!isStrictSubTree(s.left, t.left)) {
            return false;
        }

        return isStrictSubTree(s.right, t.right);
    }

}
