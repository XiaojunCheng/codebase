package com.codebase.fundation.leetcode.link;

public class _0024_Swap_Nodes_in_Pairs {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

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
