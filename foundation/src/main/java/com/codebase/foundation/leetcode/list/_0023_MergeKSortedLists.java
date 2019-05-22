package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

/**
 * @author Xiaojun.Cheng
 * @date 2019/3/20
 */
public class _0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        return ListUtil.mergeMultiLists(lists, 0, lists.length - 1);
    }

    public static void main(String[] args) {
        _0023_MergeKSortedLists main = new _0023_MergeKSortedLists();
        ListNode[] list = new ListNode[]{
                ListNode.buildListNode(new int[]{1, 4, 5}),
                ListNode.buildListNode(new int[]{1, 3, 4}),
                ListNode.buildListNode(new int[]{2, 6})
        };
        ListNode result = main.mergeKLists(list);
        ListNode.printListNode(result);
    }

}
