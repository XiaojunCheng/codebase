package com.codebase.foundation.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;


    public static TreeNode buildNode(Integer[] data) {
        TreeNode[] treeNodes = new TreeNode[data.length];
        for (int index = 0; index < data.length; index++) {
            if (data[index] != null) {
                TreeNode node = new TreeNode();
                node.val = data[index];
                treeNodes[index] = node;
            }
        }

        for (int index = 0; index < data.length / 2; index++) {
            if (treeNodes[index] != null) {
                treeNodes[index].left = treeNodes[2 * index + 1];
                treeNodes[index].right = treeNodes[2 * index + 2];
            }
        }
        return treeNodes[0];
    }

    public static void print(TreeNode root) {
        if (root == null) {
            return;
        }
        printInSecondOrder(root);
    }

    private static void printInSecondOrder(TreeNode root) {

        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);

        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.poll();
            System.out.print(node.val + ", ");
            if (node.left != null) {
                treeNodes.add(node.left);
            }
            if (node.right != null) {
                treeNodes.add(node.right);
            }
        }
    }

}
