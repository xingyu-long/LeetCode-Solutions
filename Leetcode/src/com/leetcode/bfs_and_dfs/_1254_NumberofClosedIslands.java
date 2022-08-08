package com.leetcode.bfs_and_dfs;

public class _1254_NumberofClosedIslands {
    // time:O(m * n) space:O(m * n)
    // 和surrounded region一个思路。
    public int closedIsland(int[][] grid) {
        // 先把四边的0's 以及connected的'0'全部变成 '3'
        // 其他的 '0' 转为‘2’
        // 看 ‘2’的connected component.
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        for (int i = 0; i < grid.length; i++) {
            floodZero(grid, i, 0);
            floodZero(grid, i, grid[0].length - 1);
        }

        // 坐标不要写反了
        for (int j = 0; j < grid[0].length; j++) {
            floodZero(grid, 0, j);
            floodZero(grid, grid.length - 1, j);
        }

        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    floodZero(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void floodZero(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (grid[row][col] != 0) return;
        grid[row][col] = 3;
        floodZero(grid, row + 1, col);
        floodZero(grid, row - 1, col);
        floodZero(grid, row, col + 1);
        floodZero(grid, row, col - 1);
    }
}
