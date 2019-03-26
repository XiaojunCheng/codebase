package com.codebase.foundation.leetcode.list;


import com.codebase.foundation.leetcode.ListNode;

public class _0021_MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode.printListNode(//
                ListUtil.mergeMultiLists(//
                        ListNode.buildListNode(new int[]{0, 2, 4, 5}),//
                        ListNode.buildListNode(new int[]{1, 2, 3, 4})//
                )//
        );
    }
}
