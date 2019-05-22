package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

/**
 * @author chengxiaojun
 * 空间待优化
 */
public class _0206_ReverseLinkedList {

    public static void main(String[] args) {
        int[] value1 = {1};
        ListNode l1 = ListNode.buildListNode(value1);
        ListNode.printListNode(ListUtil.reverseList(l1));
        ListNode.printListNode(ListUtil.reverseList2(l1));
        ListNode.printListNode(ListUtil.reverseList3(l1));

        int[] value2 = {5, 6, 4};
        ListNode l2 = ListNode.buildListNode(value2);
        ListNode.printListNode(ListUtil.reverseList(l2));
    }
}
