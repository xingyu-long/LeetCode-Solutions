package com.leetcode.stack_priority_queue;


import java.util.LinkedList;
import java.util.Queue;

public class _225_ImplementStackusingQueues2 {
    /**
     *  225. Implement Stack using Queues
     *  When: 2019/06/08
     *  Difficulty: Easy
     *  Review1: 2019/7/11

        solution:
        先把原来的加入tempQueue里面去，然后将现在的加入tempQueue，最后一起加入res；
     */

    Queue<Integer> queue;
    Queue<Integer> tempQueue;
    /** Initialize your data structure here. */

    public _225_ImplementStackusingQueues2() {
        queue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    // time: O(n)
    public void push(int x) {
        tempQueue.add(x);
        while (!queue.isEmpty()) {
            tempQueue.add(queue.poll());
        }
        while (!tempQueue.isEmpty()) {
            queue.add(tempQueue.poll());
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
