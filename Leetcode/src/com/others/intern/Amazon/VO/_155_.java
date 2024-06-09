package com.intern.Amazon.VO;

import java.util.Stack;

public class _155_ {
    // (1) 利用两个stack
    // （2）利用1个stack，使用内嵌类，同时保留min / https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack
    Stack<Integer> stack;
    int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public _155_() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.isEmpty()) return;
        if (stack.pop() == min) min = stack.pop();// 把一开始放入的min丢出来
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
