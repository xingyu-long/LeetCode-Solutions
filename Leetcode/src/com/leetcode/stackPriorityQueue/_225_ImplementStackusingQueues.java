package com.leetcode.stackPriorityQueue;


import java.util.LinkedList;
import java.util.Queue;

public class _225_ImplementStackusingQueues {
    /**
     *  225. Implement Stack using Queues
     *  When: 2019/06/08
     *  Difficulty: Easy
     *  Review1: 2019/7/11

        solution: 每次push的时候做一下反转
     */

    Queue<Integer> queue;
    /** Initialize your data structure here. */

    public _225_ImplementStackusingQueues() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    // time: O(n)
    public void push(int x) {
        queue.add(x);
        // 将前面的元素 全部反转一遍
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
