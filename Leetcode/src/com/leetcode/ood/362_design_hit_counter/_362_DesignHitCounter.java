package com.leetcode.ood;

import java.util.LinkedList;
import java.util.Queue;

public class _362_DesignHitCounter {
    /**
     * 362. Design Hit Counter
     * Design a hit counter which counts the number of hits received in the past 5 minutes.
     * <p>
     * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
     * <p>
     * It is possible that several hits arrive roughly at the same time.
     * <p>
     * Example:
     * HitCounter counter = new HitCounter();
     * <p>
     * // hit at timestamp 1.
     * counter.hit(1);
     * <p>
     * // hit at timestamp 2.
     * counter.hit(2);
     * <p>
     * // hit at timestamp 3.
     * counter.hit(3);
     * <p>
     * // get hits at timestamp 4, should return 3.
     * counter.getHits(4);
     * <p>
     * // hit at timestamp 300.
     * counter.hit(300);
     * <p>
     * // get hits at timestamp 300, should return 4.
     * counter.getHits(300);
     * <p>
     * // get hits at timestamp 301, should return 3.
     * counter.getHits(301);
     * <p>
     * 1, Queue
     * 2, 数组 https://www.cnblogs.com/grandyang/p/5605552.html
     */

    Queue<Integer> queue;

    public _362_DesignHitCounter() {
        queue = new LinkedList<>();
    }

    /**
     * Record a hit
     *
     * @param timestamp
     */
    public void hit(int timestamp) {
        queue.offer(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp
     * @return
     */
    public int getHits(int timestamp) {
        // 这是因为时间是递增的，所以才能这样做
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        return queue.size();
    }

    private int[] times;
    private int[] hits;

    public void DesignHitCounter2() {
        times = new int[300];
        hits = new int[300];
    }

    //第二种方法没有那么的熟悉
    public void hit2(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    public int getHits2(int timestamp) {
       int res = 0;
       for (int i = 0; i < 300; i++) {
           if (timestamp - times[i] < 300) {
               res += hits[i];
           }
       }
       return res;
    }
}
