package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

/**
 * 耗费空间太多
 * <p>
 * 参见 _0206_ReverseLinkedList
 *
 * @author Xiaojun.Cheng
 * @date 2019/3/20
 */
public class _0025_ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode curNode = head;
        int count = 0;
        while (curNode != null) {
            count++;
            if (count % k == 0) {
                break;
            }
            curNode = curNode.next;
        }

        if (curNode == null) {
            return (count % k == 0) ? ListUtil.reverseList3(head) : head;
        }

        ListNode subReverseKGroup = reverseKGroup(curNode.next, k);
        curNode.next = null;
        curNode = ListUtil.reverseList3(head);
        head.next = subReverseKGroup;

        return curNode;
    }

    public static void main(String[] args) {
        _0025_ReverseNodesInKGroup main = new _0025_ReverseNodesInKGroup();
        ListNode node1 = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode result1 = main.reverseKGroup(node1, 3);
        ListNode.printListNode(result1);

        ListNode node2 = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result2 = main.reverseKGroup(node2, 3);
        ListNode.printListNode(result2);
    }

}
