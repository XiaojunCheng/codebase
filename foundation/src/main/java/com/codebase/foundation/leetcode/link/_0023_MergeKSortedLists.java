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

        int validIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                validIndex = i;
                break;
            }
        }

        if (validIndex == -1) {
            return null;
        }

        ListNode result = lists[validIndex];
        for (int index = validIndex + 1; index < lists.length; index++) {
            if (lists[index] != null) {
                result = (result.val <= lists[index].val) ? merge2Lists(result, lists[index]) :
                        merge2Lists(lists[index], result);
            }
        }
        return result;
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = l1;
        ListNode nextNode1;
        ListNode nextNode2;
        while (true) {
            if (l2 == null) {
                return head;
            }

            if (l1.next == null) {
                l1.next = l2;
                return head;
            }

            if (l1.next.val > l2.val) {
                nextNode1 = l1.next;
                l1.next = l2;
                while (l2.next != null && nextNode1.val > l2.next.val) {
                    l2 = l2.next;
                }
                if (l2.next == null) {
                    l2.next = nextNode1;
                    return head;
                }
                nextNode2 = l2.next;
                l2.next = nextNode1;
                l2 = nextNode2;
                l1 = l1.next;
            } else {
                nextNode2 = l2.next;
                while (l1.next != null && l1.next.val <= l2.val) {
                    l1 = l1.next;
                }

                if (l1.next == null) {
                    l1.next = l2;
                    return head;
                }

                nextNode1 = l1.next;
                l1.next = l2;
                l2.next = nextNode1;
                l2 = nextNode2;
                l1 = l1.next;
            }
        }
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
