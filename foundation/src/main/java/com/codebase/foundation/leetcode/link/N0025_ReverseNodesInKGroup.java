package com.codebase.foundation.leetcode.link;

/**
 * 耗费空间太多
 *
 * @author Xiaojun.Cheng
 * @date 2019/3/20
 */
public class N0025_ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode curNode = head;
        int count = 0;
        while (curNode != null) {
            count++;
            if (count % k == 0) {
                break;
            }
            curNode = curNode.next;
        }

        if (curNode == null) {
            return (count % k == 0) ? reverseList(head) : head;
        }

        ListNode tail = head;
        ListNode subReverseKGroup = reverseKGroup(curNode.next, k);
        curNode.next = null;
        curNode = reverseList(head);
        tail.next = subReverseKGroup;

        return curNode;
    }

    public static void main(String[] args) {
        N0025_ReverseNodesInKGroup main = new N0025_ReverseNodesInKGroup();
        ListNode node1 = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode result1 = main.reverseKGroup(node1, 3);
        ListNode.printListNode(result1);

        ListNode node2 = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result2 = main.reverseKGroup(node2, 3);
        ListNode.printListNode(result2);
    }


    /**
     * 参见 0206 reverseLinkedList
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
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

}
