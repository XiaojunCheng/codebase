package com.codebase.foundation.leetcode;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode buildListNode(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode pos = head;
        for (int i = 1; i < values.length; i++) {
            pos.next = new ListNode(values[i]);
            pos = pos.next;
        }
        return head;
    }

    public static void printListNode(ListNode l) {
        while (l != null) {
            System.out.print(l.val + " -> ");
            l = l.next;
        }
        System.out.println();
    }
}
