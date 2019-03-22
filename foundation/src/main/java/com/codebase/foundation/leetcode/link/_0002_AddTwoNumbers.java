package com.codebase.foundation.leetcode.link;

import com.codebase.foundation.leetcode.ListNode;

/**
 * 20% 待优化
 */
public class _0002_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = l1;
        int addToHigh = 0;
        while (true) {
            l1.val += l2.val + addToHigh;
            addToHigh = 0;
            if (l1.val >= 10) {
                l1.val -= 10;
                addToHigh = 1;
            }
            if (l1.next == null || l2.next == null) {
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1.next == null) {
            l1.next = l2.next;
        }

        if (addToHigh <= 0) {
            return head;
        }

        while (l1.next != null) {
            l1.next.val += addToHigh;
            if (l1.next.val < 10) {
                return head;
            } else {
                l1.next.val -= 10;
                addToHigh = 1;
                l1 = l1.next;
            }
        }
        l1.next = new ListNode(addToHigh);
        return head;
    }

    public static void main(String[] args) {
        _0002_AddTwoNumbers main = new _0002_AddTwoNumbers();

        {
            int[] value1 = {2, 4, 3};
            ListNode l1 = ListNode.buildListNode(value1);
            ListNode.printListNode(l1);
            int[] value2 = {5, 6, 4};
            ListNode l2 = ListNode.buildListNode(value2);
            ListNode.printListNode(l2);
            ListNode l3 = main.addTwoNumbers(l1, l2);
            ListNode.printListNode(l3);
        }
        {
            int[] value1 = {1, 9};
            ListNode l1 = ListNode.buildListNode(value1);
            ListNode.printListNode(l1);
            int[] value2 = {9};
            ListNode l2 = ListNode.buildListNode(value2);
            ListNode.printListNode(l2);
            ListNode l3 = main.addTwoNumbers(l1, l2);
            ListNode.printListNode(l3);
        }
    }
}
