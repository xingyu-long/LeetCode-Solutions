package com.leetcode.Design;

import java.util.LinkedList;
import java.util.Queue;

public class _346_MovingAveragefromDataStream {

    /**
     *  346. Moving Average from Data Stream
     *  When: 2019/7/3

     m.next(1) = 1
     m.next(10) = (1 + 10) / 2
     m.next(3) = (1 + 10 + 3) / 3
     m.next(5) = (10 + 3 + 5) / 3

     */

    //time:O(n) space:O(n)
    private Queue<Integer> queue;
    private double sum = 0;
    private int size;

    public _346_MovingAveragefromDataStream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.remove();
        }
        sum += val;
        queue.offer(val);
        return sum / queue.size();
    }
}