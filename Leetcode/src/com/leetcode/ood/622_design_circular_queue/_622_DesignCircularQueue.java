package com.leetcode.ood;

/**
 * @Date: 06/13/2020
 * @Description: Design
 **/
public class _622_DesignCircularQueue {

    private int total;
    private int size;
    private int head;
    private int tail;
    private int[] nums;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public _622_DesignCircularQueue(int k) {
        nums = new int[k];
        total = k;
        head = 0;
        tail = 0;
        size = 0;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        tail %= total;
        // System.out.println("tail = " + tail);
        nums[tail++] = value;
        size++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head %= total;
        head++;
        size--;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return nums[head];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return nums[tail - 1];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return size == total;
    }
}
