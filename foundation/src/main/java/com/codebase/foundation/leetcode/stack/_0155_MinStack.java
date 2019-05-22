package com.codebase.foundation.leetcode.stack;

import java.util.Stack;

/**
 * Runtime: 48 ms, faster than 99.28% of Java online submissions for Min Stack.
 * Memory Usage: 37.9 MB, less than 96.22% of Java online submissions for Min Stack.
 */
public class _0155_MinStack {

    public _0155_MinStack() {
    }

    /**
     * initialize your data structure here.
     */

    private Stack<Integer> stack = new Stack();
    private Stack<Integer> minValueStack = new Stack<>();

    public void push(int x) {
        if (stack.isEmpty()) {
            minValueStack.push(x);
        } else {
            minValueStack.push(Math.min(minValueStack.peek(), x));
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        minValueStack.pop();
    }

    public int top() {
        return stack.isEmpty() ? 0 : stack.peek();
    }

    public int getMin() {
        return minValueStack.isEmpty() ? 0 : minValueStack.peek();
    }

    public static void main(String[] args) {
        _0155_MinStack minStack = new _0155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());      //--> Returns 0.
        System.out.println(minStack.getMin());   //--> Returns -2.
    }

}
