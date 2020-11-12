package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 10/26/2020
 * @Description: BFS
 **/
public class _1091_Shortest_Path_in_Binary_Matrix {
    public int shortestPathBinaryMatrix(int[][] grid) {

        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == m - 1 && curr[1] == n - 1) return curr[2];
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    int x = curr[0] + i, y = curr[1] + j;
                    if (isValid(grid, x, y, m, n)) {
                        grid[x][y] = 1;
                        queue.offer(new int[]{x, y, curr[2] + 1});
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValid(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= m) return false;
        if (grid[i][j] != 0) return false;
        return true;
    }
}
