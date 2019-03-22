package com.codebase.foundation.leetcode.link;

/**
 * @author Xiaojun.Cheng
 * @date 2019/3/20
 */
public class _0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        return merge2Lists(lists, 0, lists.length - 1);
    }

    private ListNode merge2Lists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        if (start + 1 == end) {
            return merge2Lists(lists[start], lists[end]);
        }

        int mid = (end - start) / 2 + start;
        return merge2Lists(merge2Lists(lists, start, mid), merge2Lists(lists, mid + 1, end));
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
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
        _0023_MergeKSortedLists main = new _0023_MergeKSortedLists();

        ListNode[] list = new ListNode[]{
                ListNode.buildListNode(new int[]{1, 4, 5}),
                ListNode.buildListNode(new int[]{1, 3, 4}),
                ListNode.buildListNode(new int[]{2, 6})
        };
        ListNode result = main.mergeKLists(list);
        ListNode.printListNode(result);
    }

}
