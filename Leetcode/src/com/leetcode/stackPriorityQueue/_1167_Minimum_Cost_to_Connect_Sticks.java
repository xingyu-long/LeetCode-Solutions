package com.leetcode.stackPriorityQueue;

import java.util.PriorityQueue;

/**
 * @Date: 10/25/2020
 * @Description: PQ
 **/
public class _1167_Minimum_Cost_to_Connect_Sticks {
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length <= 1) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.offer(s);
        }
        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            res += sum;
            pq.offer(sum);
        }
        return res;
    }
}
