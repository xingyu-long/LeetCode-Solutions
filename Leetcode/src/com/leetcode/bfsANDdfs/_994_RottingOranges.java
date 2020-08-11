package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/02/2020
 * @Description: BFS,
 **/
public class _994_RottingOranges {
    // 直接修改其值，不用visited数组
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int fresh = 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) return 0;
        int res = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0], y = curr[1] + dir[1];
                    if (x < 0 || x >= grid.length || y  < 0 || y >= grid[0].length) continue;
                    if (grid[x][y] != 1) continue;
                    fresh--;
                    queue.offer(new int[]{x, y});
                    grid[x][y] = 2;
                }
            }
            res++;
        }
        return fresh > 0 ? -1 : res - 1;
    }
}
