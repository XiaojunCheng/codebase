package com.codebase.foundation.leetcode.list;

import com.codebase.foundation.leetcode.ListNode;

import java.util.Stack;

public class _0234_Palindrome_Linked_List {

    /**
     * Runtime: 4 ms, faster than 15.32% of Java online submissions for Palindrome Linked List.
     * Memory Usage: 42.1 MB, less than 61.42% of Java online submissions for Palindrome Linked List.
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        ListNode t = head;
        while (t != null) {
            stack.add(t.val);
            t = t.next;
        }

        while (head != null) {
            if (head.val != stack.pop().intValue()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        _0234_Palindrome_Linked_List main = new _0234_Palindrome_Linked_List();
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1, 2})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1, 2, 2})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1, 2, 1})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1, 2, 2, 1})));
        System.out.println(main.isPalindrome(ListNode.buildListNode(new int[]{1, 2, 3, 2, 1})));
    }

}
