/*
 * @Date: 01/26/2021 09:56:01
 * @LastEditTime: 01/26/2021 10:02:25
 * @Description: Binary Search / Dijkstra / Union Find
 */
package com.leetcode.graph.shortest_path;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _1631_PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0] == null || heights[0].length == 0) {
            return 0;
        }
        int[] DIR = new int[] { 0, 1, 0, -1, 0 };

        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.offer(new int[] { 0, 0, 0 });
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], r = curr[1], c = curr[2];
            if (d > dist[r][c])
                continue; // 表示当前这个不是最短的情况
            if (r == m - 1 && c == n - 1)
                return d;
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newDist = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (dist[nr][nc] > newDist) {
                        dist[nr][nc] = newDist;
                        pq.offer(new int[] { dist[nr][nc], nr, nc });
                    }
                }
            }
        }
        return 0;
    }
}
