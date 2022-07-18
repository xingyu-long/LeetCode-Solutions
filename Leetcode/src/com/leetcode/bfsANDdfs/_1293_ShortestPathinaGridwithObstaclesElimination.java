/*
 * @Date: 07/17/2022 14:39:50
 * @LastEditTime: 07/17/2022 14:40:53
 * @Description: You need to fill out
 */
package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1293_ShortestPathinaGridwithObstaclesElimination {
    // time: O(M * N)
    // space: O(M * N * K)
    public int shortestPath(int[][] grid, int k) {
        // BFS
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][k + 1];
        // {x, y, usedK}
        queue.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        int step = 0;
        
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1], currK = curr[2];
                if (x == m - 1 && y == n - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;
                    int nextK = currK;
                    // we have to remove this obstacle
                    if (grid[newX][newY] == 1) {
                        nextK++;
                    }
                    if (nextK <= k && !visited[newX][newY][nextK]) {
                        queue.offer(new int[]{newX, newY, nextK});
                        visited[newX][newY][nextK] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
