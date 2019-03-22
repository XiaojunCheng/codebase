package com.codebase.foundation.leetcode.link;


public class _0021_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode head = l1;
        if (l1.val > l2.val) {
            l1 = l2;
            l2 = head;
            head = l1;
        }

        ListNode curNode = l1;
        l1 = l1.next;
        boolean left = true;
        while (l1 != null && l2 != null) {
            if (left) {
                while (l1.val <= l2.val) {
                    curNode = l1;
                    l1 = l1.next;
                    if (l1 == null) {
                        curNode.next = l2;
                        return head;
                    }
                }
                curNode.next = l2;
                l2 = l2.next;
                curNode = curNode.next;
                left = false;
            } else {
                while (l1.val > l2.val) {
                    curNode = l2;
                    l2 = l2.next;
                    if (l2 == null) {
                        curNode.next = l1;
                        return head;
                    }
                }
                curNode.next = l1;
                l1 = l1.next;
                curNode = curNode.next;
                left = true;
            }
        }

        curNode.next = l1 == null ? l2 : l1;
        return head;
    }

    public static void main(String[] args) {
        _0021_MergeTwoSortedLists main = new _0021_MergeTwoSortedLists();
        ListNode.printListNode(//
                main.mergeTwoLists(//
                        ListNode.buildListNode(new int[]{0, 2, 4, 5}),//
                        ListNode.buildListNode(new int[]{1, 2, 3, 4})//
                )//
        );
    }
}
