package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
 * Memory Usage: 38.7 MB, less than 31.31% of Java online submissions for Linked List Cycle.
 */
public class _0141_Linked_List_Cycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode nextNode = head.next.next;
        head = head.next;
        while (head != null && nextNode != null) {
            if (head == nextNode) {
                return true;
            }

            if (nextNode.next == null) {
                return false;
            }

            head = head.next;
            nextNode = nextNode.next.next;
        }
        return false;
    }

}
