package com.codebase.fundation.leetcode;

/**
 * 20% 待优化
 */
public class _0002_Add_Two_Numbers {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l2;
        }

        ListNode head = l1;
        ListNode pos1 = l1;
        ListNode pos2 = l2;
        int addToHigh = 0;
        while (true) {
            pos1.val += pos2.val + addToHigh;
            addToHigh = 0;
            if (pos1.val >= 10) {
                pos1.val -= 10;
                addToHigh = 1;
            }
            if (pos1.next == null || pos2.next == null) {
                break;
            }
            pos1 = pos1.next;
            pos2 = pos2.next;
        }

        if (pos1.next == null && pos2.next == null) {
            if (addToHigh > 0) {
                pos1.next = new ListNode(addToHigh);
                return head;
            }
        }

        if (pos1.next == null) {
            pos1.next = pos2.next;
        }
        if (addToHigh <= 0) {
            return head;
        }

        while (true) {
            pos1.next.val += addToHigh;
            addToHigh = 0;
            if (pos1.next.val < 10) {
                return head;
            }

            pos1.next.val -= 10;
            addToHigh = 1;
            pos1 = pos1.next;
            if (pos1.next == null) {
                pos1.next = new ListNode(addToHigh);
                return head;
            }
        }
    }

    public static void main(String[] args) {
        int[] value1 = {2, 4, 3};
        //        int[] value1 = {2, 4, 5, 9, 9};
        //        int[] value1 = {5};
        ListNode l1 = buildListNode(value1);
        printListNode(l1);
        int[] value2 = {5, 6, 4};
        //        int[] value2 = {};
        ListNode l2 = buildListNode(value2);
        printListNode(l2);
        ListNode l3 = addTwoNumbers(l1, l2);
        printListNode(l3);
    }

    private static void printListNode(ListNode l) {
        while (l != null) {
            System.out.print(l.val + " -> ");
            l = l.next;
        }
        System.out.println();
    }

    private static ListNode buildListNode(int[] values) {
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
}
