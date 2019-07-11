package com.leetcode.stackPriorityQueue;


import java.util.Stack;

public class _155_MinStack {
    /**
     *  155. Min Stack
     *  When: 2019/06/08
     *  Difficulty:Easy
     *  Review1: 2019/7/11
     *
     *  solution:
     *  (1) 使用两个stack来记录，其中一个来记录每次的最小值；主要是注意push和pop操作（均要考虑到对minStack的处理）
     */
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    // time: O(1) space: O(n)
    public _155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int min = minStack.peek();//当前的最小值
            if (x <= min) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        int x = stack.pop();
        if (x == minStack.peek()) { //相当于相同的话，就需要把这个弹出去，因为没有了这个数，这个也不能作为最小的值存在
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
