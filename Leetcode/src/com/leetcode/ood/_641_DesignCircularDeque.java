package com.leetcode.ood;

/**
 * @Date: 06/13/2020
 * @Description: Design, head, tail
 **/
public class _641_DesignCircularDeque {

    private int head, tail;
    private int size, total;
    private int[] nums;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public _641_DesignCircularDeque(int k) {
        head = 0;
        tail = k - 1;
        nums = new int[k];
        size = 0;
        total = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        head -= 1;
        head += total;
        head %= total;
        nums[head] = value;
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        tail += 1;
        tail %= total;
        nums[tail] = value;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head += 1;
        head %= total;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail -= 1;
        tail += total;
        tail %= total;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return nums[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return nums[tail];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == total;
    }
}
