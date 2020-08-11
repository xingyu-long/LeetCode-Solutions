package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 06/05/2020
 * @Description: DFS, BFS, Union Find, Island
 **/
public class _695_MaxAreaofIsland {

    // time:O(m*n) space:O(m*n)
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int count = bfs(grid, i, j);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;// marked;
        int count = 0;
        int left = dfs(grid, i, j - 1);
        int right = dfs(grid, i, j + 1);
        int up = dfs(grid, i - 1, j);
        int down = dfs(grid, i + 1, j);
        count += left + right + up + down + 1;
        return count;
    }

    private int bfs(int[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int count = 1;
        grid[i][j] = 2;
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (isValid(grid, x, y)) {
                    count++;
                    grid[x][y] = 2;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return count;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] != 1) {
            return false;
        }
        return true;
    }
}
