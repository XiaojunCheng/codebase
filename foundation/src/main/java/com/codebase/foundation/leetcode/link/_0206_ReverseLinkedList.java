package com.codebase.foundation.leetcode.link;

/**
 * @author chengxiaojun
 *         空间待优化
 */
public class _0206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head;
        ListNode newNode = head.next;
        newHead.next = null;
        head = newNode;
        while (head != null) {
            newNode = head.next;
            head.next = newHead;
            newHead = head;
            head = newNode;
        }
        return newHead;
    }

    public static void main(String[] args) {
        _0206_ReverseLinkedList main = new _0206_ReverseLinkedList();
        int[] value1 = {1};
        ListNode l1 = ListNode.buildListNode(value1);
        ListNode.printListNode(main.reverseList(l1));

        int[] value2 = {5, 6, 4};
        ListNode l2 = ListNode.buildListNode(value2);
        ListNode.printListNode(main.reverseList(l2));
    }
}
