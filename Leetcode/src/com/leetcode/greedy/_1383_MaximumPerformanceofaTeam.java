package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _1383_MaximumPerformanceofaTeam {
    /**
     * When:03/15/2020
     * @param n
     * @param speed
     * @param efficiency
     * @param k
     * @return
     */
    // sort: nlogn pq: nlogk space: O(2 * n)
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i] = new int[] {efficiency[i], speed[i]};
        }
        // 按照efficiency逆序
        Arrays.sort(data, (a, b) -> (b[0] - a[0]));
        // 按照speed 增序a,
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = 0, sum = 0;
        long MOD = (long) Math.pow(10, 9) + 7;
        for (int[] es : data) {
            pq.offer(es[1]);
            sum = (sum + es[1]);
            if (pq.size() > k)
                sum -= pq.poll();
            res = Math.max(res, (sum * es[0]));
        }
        return (int) (res % MOD);
    }

}
