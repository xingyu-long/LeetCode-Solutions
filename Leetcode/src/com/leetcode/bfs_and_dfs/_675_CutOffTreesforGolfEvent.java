package com.leetcode.bfs_and_dfs;

import java.util.*;

public class _675_CutOffTreesforGolfEvent {
    public int cutOffTree(List<List<Integer>> forest) {
        // 通用的BFS，只是理解题目意思上之前有点问题。
        if (forest == null || forest.size() == 0) {
            return 0;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }

        int[] start = new int[]{0, 0};
        int res = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int step = BFS(forest, start, curr);
            if (step < 0) return -1;
            res += step;
            start[0] = curr[0];
            start[1] = curr[1];
        }
        return res;
    }

    public int BFS(List<List<Integer>> forest, int[] start, int[] end) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == end[0] && curr[1] == end[1]) return step;

                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (forest.get(x).get(y) == 0) continue;
                    if (visited[x][y]) continue;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;
        }
        return -1;
    }
}
