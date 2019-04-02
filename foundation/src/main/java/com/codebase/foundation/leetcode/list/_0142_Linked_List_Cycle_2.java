package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

public class _0142_Linked_List_Cycle_2 {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
     * Memory Usage: 35 MB, less than 26.98% of Java online submissions for Linked List Cycle II.
     *
     * @see <a href="https://leetcode.com/problems/linked-list-cycle-ii/discuss/44793/O(n)-solution-by-using-two-pointers-without-change-anything"/>
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode node = head.next;
        ListNode nextNode = head.next.next;
        while (nextNode != null) {
            if (node == nextNode) {
                break;
            }
            if (nextNode.next == null) {
                return null;
            }
            node = node.next;
            nextNode = nextNode.next.next;
        }

        if (nextNode == null) {
            return null;
        }

        node = head;
        while (node != nextNode) {
            node = node.next;
            nextNode = nextNode.next;
        }
        return node;
    }

    public static void main(String[] args) {
        _0142_Linked_List_Cycle_2 main = new _0142_Linked_List_Cycle_2();
        ListNode.printListNode(main.detectCycle(null));
    }

}
