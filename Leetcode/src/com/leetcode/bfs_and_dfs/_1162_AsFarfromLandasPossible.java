/*
 * @Date: 2020-03-29 15:57:14
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-29 16:31:46
 */
package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _1162_AsFarfromLandasPossible {

     // wells and gates的题 反过来想
    // 先用DFS 验证
    // time: O(m * n * k(1的个数))
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0; // 改成0然后修改棋盘内的值。这里和后面的dfs里面的判断也很关键
                    dfs(grid, i, j, 1); // 这样来 就没有可能会有被修改出来的1
                }
            }
        }
        int res = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) res = Math.max(res, grid[i][j] - 1); 
            }
        }
        return res;
    }
    
    
    public void dfs(int[][] grid, int i, int j, int dist) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] != 0 && grid[i][j] <= dist) return; // 这样1也不会被覆盖
        grid[i][j] = dist;
        dfs(grid, i + 1, j, dist + 1);
        dfs(grid, i - 1, j, dist + 1);
        dfs(grid, i, j + 1, dist + 1);
        dfs(grid, i, j - 1, dist + 1);
    }
    
    // BFS 这个更加的高效，并且一起并行运行？
    // time:O(m * n)
    public int maxDistance2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    // 需要先加入，防止后面重复加入。
                }
            }
        }
        // 需要先标记，否则会重复加入。
        int level = -1; // 最后要把自己那一层先减去
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                for (int[] dir : dirs) {
                    int x = currX + dir[0];
                    int y = currY + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && 
                        !visited[x][y] && grid[x][y] == 0) {
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            level++;
        }
        // 因为最后要把自己谈出来，所以多了一层
        return level == 0 ? -1 : level;
    }
}