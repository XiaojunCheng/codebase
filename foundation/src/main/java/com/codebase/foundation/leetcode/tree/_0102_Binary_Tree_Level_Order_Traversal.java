package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _0102_Binary_Tree_Level_Order_Traversal {

    /**
     * 非递归算法
     * Runtime: 1 ms, faster than 74.81% of Java online submissions for Binary Tree Level Order Traversal.
     * Memory Usage: 37.4 MB, less than 32.65% of Java online submissions for Binary Tree Level Order Traversal.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> ret = new ArrayList<>();
        while (!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> levelElem = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode elem = queue.poll();
                levelElem.add(elem.val);
                if (elem.left != null) {
                    queue.add(elem.left);
                }
                if (elem.right != null) {
                    queue.add(elem.right);
                }
            }
            ret.add(levelElem);
        }
        return ret;
    }
}
