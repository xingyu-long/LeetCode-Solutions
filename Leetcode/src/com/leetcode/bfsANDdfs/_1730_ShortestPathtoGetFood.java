/*
 * @Date: 07/16/2022 20:23:53
 * @LastEditTime: 07/16/2022 20:24:57
 * @Description: You need to fill out
 */
package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1730_ShortestPathtoGetFood {
    int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // use visited array to avoid TLE
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    queue.offer(new int[] { i, j });
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (grid[curr[0]][curr[1]] == '#') {
                    return step;
                }
                for (int[] dir : dirs) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];
                    if (isValid(newX, newY, grid, visited)) {
                        queue.offer(new int[] { newX, newY });
                        visited[newX][newY] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public boolean isValid(int i, int j, char[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (visited[i][j])
            return false;
        if (grid[i][j] == 'X')
            return false;
        return true;
    }
}