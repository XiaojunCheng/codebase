package com.codebase.foundation.leetcode.stack;

public class _0155_MinStack {

    /**
     * initialize your data structure here.
     */
    public _0155_MinStack() {

    }

    public void push(int x) {
    }

    public void pop() {
    }

    public int top() {
        return 0;
    }

    public int getMin() {
        return 0;
    }

    public static void main(String[] args) {
        _0155_MinStack minStack = new _0155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   //--> Returns -3.
        minStack.pop();
        minStack.top();      //--> Returns 0.
        minStack.getMin();   //--> Returns -2.
    }

}
