package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/02/2020
 * @Description: BFS,
 **/
public class _994_RottingOranges {
    // 同时也记录一下fresh的情况，最后判断的时候给出。并且没有改变原来的数组
    // time:O(m * n) space:O(m * n)
    public int orangesRotting(int[][] grid) {
        // 用BFS
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; // 表示加入坏的了
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) return 0;
        int time = 0;
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                for (int[] dir : dirs) {
                    int x = currX + dir[0];
                    int y = currY + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (visited[x][y]) continue;
                    if (grid[x][y] == 1) {
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                        fresh--;
                    }
                }
            }
            time++;
        }
        return fresh == 0 ? time - 1 : -1;
    }
}
