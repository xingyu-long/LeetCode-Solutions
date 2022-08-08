package com.leetcode.stack_priority_queue;

import java.util.Stack;

public class _232_ImplementQueueusingStacks2 {
    /**
     *  232. Implement Queue using Stacks
     *  When: 2019/06/08
     *  Difficulty: Easy
     *  Review1: 2019/7/11
     *
        solution:
        每次操作都需要把原来的数据弄到temp，然后加入新的，最后再到结果stack中
     */

    Stack<Integer> s1;
    Stack<Integer> s2;

    /** Initialize your data structure here. */

    public _232_ImplementQueueusingStacks2() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    // 都是放入s1里面 time: O(n)
    // s2作为临时栈
    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    // time:O(1);
    public int pop() {
        return s1.pop();
    }

    /** Get the front element. */
    // 跟上面的pop一样的道理
    public int peek() {
        return s1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
