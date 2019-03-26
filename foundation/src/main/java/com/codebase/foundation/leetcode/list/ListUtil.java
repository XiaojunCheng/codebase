package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

public class ListUtil {

    /**
     * 非递归，代码比较丑陋
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head;
        ListNode newNode = head.next;
        newHead.next = null;
        head = newNode;
        while (head != null) {
            newNode = head.next;
            head.next = newHead;
            newHead = head;
            head = newNode;
        }
        return newHead;
    }

    /**
     * 优化版本
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;
        head.next = null;

        ListNode newHead = reverseList2(nextNode);
        nextNode.next = head;
        return newHead;
    }

    /**
     * 最终优化版本
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //======================= 归并
    public static ListNode mergeMultiLists(ListNode l1, ListNode l2) {
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

    public static ListNode mergeMultiLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        if (start + 1 == end) {
            return ListUtil.mergeMultiLists(lists[start], lists[end]);
        }

        int mid = (end - start) / 2 + start;
        return ListUtil.mergeMultiLists(ListUtil.mergeMultiLists(lists, start, mid), ListUtil.mergeMultiLists(lists, mid + 1, end));
    }

}
