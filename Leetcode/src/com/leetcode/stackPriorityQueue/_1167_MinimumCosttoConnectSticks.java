package com.leetcode.stackPriorityQueue;

import java.util.PriorityQueue;

/**
 * @Date: 08/02/2020
 * @Description: PriorityQueue
 **/
public class _1167_MinimumCosttoConnectSticks {
    // 每次取出两个小的来构成。
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int stick : sticks) {
            pq.offer(stick);
        }

        int res = 0;
        while (pq.size() != 1) {
            int first = pq.poll();
            int second = pq.poll();
            int merge = first + second;
            res += merge;
            pq.offer(merge);
        }
        return res;
    }
}
