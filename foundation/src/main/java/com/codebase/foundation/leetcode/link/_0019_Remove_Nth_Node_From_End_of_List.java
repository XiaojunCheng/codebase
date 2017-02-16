package com.codebase.foundation.leetcode.link;

public class _0019_Remove_Nth_Node_From_End_of_List {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (n <= 0) {
            return head;
        }

        if (head.next == null) {
            return null;
        }

        ListNode flagOne = head;
        ListNode flagTwo = head;
        for (int i = 1; i <= n; i++) {
            flagTwo = flagTwo.next;
        }

        if (flagTwo == null) {
            return flagOne.next;
        }

        while (flagTwo.next != null) {
            flagOne = flagOne.next;
            flagTwo = flagTwo.next;
        }
        flagOne.next = flagOne.next.next;
        return head;
    }

    public static void printListNode(ListNode head) {
        ListNode pos = head;
        System.out.println("======");
        while (pos != null) {
            System.out.print(pos.val + "->");
            pos = pos.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _0019_Remove_Nth_Node_From_End_of_List main = new _0019_Remove_Nth_Node_From_End_of_List();

        int[][] data = {//
                {2},//
                {2, 1},//
                {2, 1},//
                {3, 5, 6, 9},//
                {1, 2, 3, 4, 5},//
                {1, 2, 3, 4, 5}//
        };

        int[] index = {//
                1,//
                1,//
                2,//
                3,//
                1,//
                5//
        };

        for (int i = 0; i < index.length; i++) {
            ListNode head = ListNode.buildListNode(data[i]);
            printListNode(main.removeNthFromEnd(head, index[i]));
        }
    }
}
