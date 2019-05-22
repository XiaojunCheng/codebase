package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

/**
 * Runtime: 1 ms, faster than 98.75% of Java online submissions for Intersection of Two Linked Lists.
 * Memory Usage: 40.3 MB, less than 7.45% of Java online submissions for Intersection of Two Linked Lists.
 */
public class _0160_IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = getLength(headA);
        int len2 = getLength(headB);

        if (len1 == 0 || len2 == 0) {
            return null;
        }

        if (len1 >= len2) {
            while (len1 > len2) {
                len1--;
                headA = headA.next;
            }
        } else {
            while (len2 > len1) {
                len2--;
                headB = headB.next;
            }
        }

        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private int getLength(ListNode headA) {
        int len = 0;
        while (headA != null) {
            len++;
            headA = headA.next;
        }
        return len;
    }

    public static void main(String[] args) {
        //listA = [4,1,8,4,5], listB = [
//        _0160_IntersectionOfTwoLinkedLists main = new _0160_IntersectionOfTwoLinkedLists();
//        ListNode nodeA = ListNode.buildListNode(new int[]{4, 1, 8, 4, 5});
//        ListNode nodeB = ListNode.buildListNode(new int[]{5, 0, 1, 8, 4, 5});
//        ListNode.printListNode(main.getIntersectionNode(nodeA, nodeB));
//
//        ListNode nodeC = ListNode.buildListNode(new int[]{2, 6, 4});
//        ListNode nodeD = ListNode.buildListNode(new int[]{1, 5});
//        ListNode.printListNode(main.getIntersectionNode(nodeC, nodeD));
    }
}
