package com.codebase.foundation.leetcode.link;

/**
 * 20% 待优化
 */
public class _0002_Add_Two_Numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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
        _0002_Add_Two_Numbers main = new _0002_Add_Two_Numbers();
        int[] value1 = {2, 4, 3};
        ListNode l1 = ListNode.buildListNode(value1);
        ListNode.printListNode(l1);
        int[] value2 = {5, 6, 4};
        ListNode l2 = ListNode.buildListNode(value2);
        ListNode.printListNode(l2);
        ListNode l3 = main.addTwoNumbers(l1, l2);
        ListNode.printListNode(l3);
    }
}
