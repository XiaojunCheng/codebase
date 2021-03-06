package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

public class _0104_Maximum_Depth_of_Binary_Tree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
