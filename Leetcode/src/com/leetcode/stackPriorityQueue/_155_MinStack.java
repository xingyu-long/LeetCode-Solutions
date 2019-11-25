package com.leetcode.stackPriorityQueue;


import java.util.Stack;

public class _155_MinStack {
    /**
     *  155. Min Stack
     *  When: 2019/06/08
     *  Difficulty:Easy
     *  Review1: 2019/7/11
     *  review2: 2019/9/2
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
    // 不用考虑哪些比当前最小值大的那些数，因为只能被pop出去或者是getMin也与其无关
    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int min = minStack.peek();//当前的最小值
            if (x <= min) { // 这里也需要注意
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


    // 这个one stack太骚了
    class MinStack {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;
        /** initialize your data structure here. */
        public MinStack() {
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

}
