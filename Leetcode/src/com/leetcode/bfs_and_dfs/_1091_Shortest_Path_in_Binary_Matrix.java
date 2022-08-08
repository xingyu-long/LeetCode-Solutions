/*
 * @Date: 10/26/2020 10:13:45
 * @LastEditTime: 02/13/2021 09:22:09
 * @Description: BFS
 */
package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1091_Shortest_Path_in_Binary_Matrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // BFS
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1)
            return -1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[] { 0, 0, 1 });
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], dist = curr[2];
            if (x == m - 1 && y == n - 1)
                return dist;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    int newX = x + i, newY = y + j;
                    if (isValid(grid, visited, newX, newY)) {
                        queue.offer(new int[] { newX, newY, dist + 1 });
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 0 || visited[x][y]) {
            return false;
        }
        return true;
    }
}
