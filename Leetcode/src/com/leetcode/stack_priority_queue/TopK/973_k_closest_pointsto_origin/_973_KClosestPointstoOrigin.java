package com.leetcode.stack_priority_queue.TopK;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date: 05/06/2020, 05/30/2020
 * @Description: TopK, MAX-PQ, QUICK-SELECTION
 **/
public class _973_KClosestPointstoOrigin {

    // time: O(nlogK)
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return new int[][]{};
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> dist(b) - dist(a));
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[pq.size()][2];
        int n = pq.size();
        // 给出的顺序并没有关系
        for (int i = 0; i < n; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    private int dist(int[] point) { // 这样不会有精度丢失
        int x = point[0], y = point[1];
        return x * x + y * y;
    }
}
