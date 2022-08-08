package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _847_ShortestPathVisitingAllNodes {
    // 比较难的BFS
    public int shortestPathLength(int[][] graph) {
        // 利用状态压缩
        int n = graph.length;
        int allState = 0;
        for (int i = 0; i < n; i++) {
            allState |= (1 << i);
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            int mark = 1 << i;
            visited[i][mark] = true;
            queue.offer(new int[]{i, mark});
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int curr = node[0];
                int state = node[1];
                if  (state == allState) return step;
                int[] nexts = graph[curr];
                for (int next : nexts) {
                    int nextState = state | (1 << next);
                    if (visited[next][nextState]) continue;
                    visited[next][nextState] = true;
                    queue.offer(new int[]{next, nextState});
                }
            }
            step++;
        }
        return -1;
    }
}
