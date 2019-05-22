package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

public class _0024_SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = newHead.next;
        newHead.next = head;

        ListNode tmpNode;
        while (head.next != null) {
            if (head.next.next == null) {
                break;
            }

            tmpNode = head.next.next;
            head.next.next = tmpNode.next;
            tmpNode.next = head.next;
            head.next = tmpNode;
            head = tmpNode.next;
        }

        return newHead;
    }

    public static void main(String[] args) {

    }
}
