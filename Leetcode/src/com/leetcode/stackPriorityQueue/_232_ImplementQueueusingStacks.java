package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _232_ImplementQueueusingStacks {
    Stack<Integer> s1;
    Stack<Integer> s2;

    /** Initialize your data structure here. */
    /**
     * 232. Implement Queue using Stacks
        When: 2019/06/08
        solution：主要使用两个stack，第二个就是用来返回queue的序列，这里就要主要当s2为空的时候把s1的pop然后push进去
     */
    public _232_ImplementQueueusingStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    // 都是放入s1里面 time: O(1)
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    //如果s2为空的话，就把s1里面的pop结果push到s2 然后s2.pop() 不然的话就直接s2.pop()
    // time: O(n)
    public int pop() {
        if (!s2.isEmpty()) return s2.pop();
        else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }

    /** Get the front element. */
    // 跟上面的pop一样的道理
    public int peek() {
        if (!s2.isEmpty()) return s2.peek();
        else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
