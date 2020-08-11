package com.leetcode.stackPriorityQueue.monoStack;

import java.util.Stack;

public class _901_OnlineStockSpan {

    // 相当于维护一个单调递减的栈，并且这里应该是pair，方便书写所以写的两个栈同步。一个记录price，一个记录最长的连续长度。
    Stack<Integer> stack;
    Stack<Integer> count;

    public _901_OnlineStockSpan() {
        stack = new Stack<>();
        count = new Stack<>();
    }

    // amortized O(1)
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && price >= stack.peek()) {
            stack.pop();
            span += count.pop();
        }
        stack.push(price);
        count.push(span);
        return span;
    }
}
