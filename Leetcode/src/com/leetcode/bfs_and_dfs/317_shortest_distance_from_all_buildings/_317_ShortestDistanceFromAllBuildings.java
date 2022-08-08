/*
 * @Date: 06/21/2022 10:29:39
 * @LastEditTime: 06/21/2022 10:51:57
 * @Description: BFS, matrix
 */
package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _317_ShortestDistanceFromAllBuildings {

    private int[][] dirs = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};

    // time: O(m * n * m * n)
    // space: O(m * n)
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int buildingTotal = 0;
        int[][] reachBuild = new int[m][n]; // from (i, j), how many buildings it can reach.
        int[][] dist = new int[m][n]; // distance for (i, j) to reach all buildings. 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingTotal++;
                    bfs(grid, reachBuild, dist, i, j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (reachBuild[i][j] == buildingTotal && dist[i][j] < res) {
                        res = dist[i][j];
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;   
    }


    public void bfs(int[][] grid, int[][] reachBuild, int[][] dist, int x, int y) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int level = 1; // use level for distance
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];
                    if (isValid(grid, visited, newX, newY)) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                        dist[newX][newY] += level;
                        reachBuild[newX][newY] += 1;
                    }
                }
            }
            level += 1; // update the distance
        }
    }


    public boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
        if (visited[x][y]) return false;
        if (grid[x][y] != 0) return false;
        return true;
    }
}
