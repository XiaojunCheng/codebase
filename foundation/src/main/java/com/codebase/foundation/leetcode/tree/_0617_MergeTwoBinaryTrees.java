package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

/**
 * Runtime: 5 ms, faster than 100.00% of Java online submissions for Merge Two Binary Trees.
 * Memory Usage: 41.7 MB, less than 26.21% of Java online submissions for Merge Two Binary Trees.
 */
public class _0617_MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }

        t1.val += t1.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

}
