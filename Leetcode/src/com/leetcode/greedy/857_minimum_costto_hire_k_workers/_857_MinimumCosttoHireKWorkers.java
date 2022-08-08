package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _857_MinimumCosttoHireKWorkers {
    /**
     * When: 03/15/2020
     * @param quality
     * @param wage
     * @param K
     * @return
     */
    // time: sort: nlogn, pq: nlogk. space: O(n * 2)
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        // quality, >= min 一开始看起来就像是search的题目
        // 3, 10, 10
        // 4,  2,  2
        if (quality == null || quality.length == 0 || 
            wage == null || wage.length == 0) return 0.0;
        int n = quality.length;
        double[][] workers = new double[n][2];
        for (int i = 0; i < n; i++) {
            workers[i] = new double[] {(1.0 * wage[i]) / quality[i], quality[i]};
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));// asc
        PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> Double.compare(b, a));
        double qSum = 0;
        double res = Integer.MAX_VALUE;
        // (w / q) * q_sum -> w_sum
        for (double[] worker : workers) {
            qSum += worker[1];
            pq.offer(worker[1]);
            if (pq.size() > K) qSum -= pq.poll();
            if (pq.size() == K) res = Math.min(res, qSum * worker[0]);
        }
        return res;
    }
    
}