package com.leetcode.stackPriorityQueue;

import java.util.PriorityQueue;

/**
 * @Date: 04/12/2020
 * @Description: PriorityQueue
 **/
public class _1046_LastStoneWeight {

    // time:O(nlogn) space:O(n)
    public int lastStoneWeight(int[] stones) {
        // use the heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int max = pq.poll();
            int max2 = pq.poll();
            if (max == max2) {
                continue;
            } else {
                pq.offer(max - max2);
            }
        }
        if (pq.isEmpty()) {
            return 0;
        } else {
            return pq.peek();
        }
    }
}
