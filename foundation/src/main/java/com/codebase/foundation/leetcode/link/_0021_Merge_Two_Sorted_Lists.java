package com.codebase.foundation.leetcode.link;


public class _0021_Merge_Two_Sorted_Lists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode pos = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pos.next = l1;
                l1 = l1.next;
            } else {
                pos.next = l2;
                l2 = l2.next;
            }
            pos = pos.next;
        }
        pos.next = (l1 == null) ? l2 : l1;

        return head;
    }

    public static void main(String[] args) {
        _0021_Merge_Two_Sorted_Lists main = new _0021_Merge_Two_Sorted_Lists();
        ListNode.printListNode(//
                main.mergeTwoLists(//
                        ListNode.buildListNode(new int[]{0, 2, 4, 5}),//
                        ListNode.buildListNode(new int[]{1, 2, 3, 4})//
                )//
        );
    }
}
