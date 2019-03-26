package com.codebase.foundation.leetcode.tree;

import com.codebase.foundation.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Runtime: 6 ms, faster than 95.99% of Java online submissions for Convert BST to Greater Tree.
 * Memory Usage: 41.6 MB, less than 53.15% of Java online submissions for Convert BST to Greater Tree.
 */
public class _0538_Convert_BST_to_Greater_Tree {

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        recursiveTravel(root, nodeQueue);

        TreeNode node = nodeQueue.poll();
        int preValue = node.val;
        while (!nodeQueue.isEmpty()) {
            TreeNode nodeSmaller = nodeQueue.peek();
            int addVal = (nodeSmaller.val == preValue) ? node.val - nodeSmaller.val : node.val;
            preValue = nodeSmaller.val;
            nodeSmaller.val += addVal;

            node = nodeQueue.poll();
        }

        return root;
    }

    private void recursiveTravel(TreeNode root, Queue<TreeNode> nodeQueue) {
        if (root.right != null) {
            recursiveTravel(root.right, nodeQueue);
        }
        nodeQueue.add(root);
        if (root.left != null) {
            recursiveTravel(root.left, nodeQueue);
        }
    }

    public static void main(String[] args) {
        _0538_Convert_BST_to_Greater_Tree main = new _0538_Convert_BST_to_Greater_Tree();
        TreeNode treeNode = TreeNode.buildNode(new Integer[]{5, 2, 13, 1, 3});
        TreeNode.print(main.convertBST(treeNode));
    }

}
