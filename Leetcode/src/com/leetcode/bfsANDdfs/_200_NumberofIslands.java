package com.leetcode.bfsANDdfs;

public class _200_NumberofIslands {

    /**
     * 200. Number of Islands
     * When: 2019/05/31
     *
     * solution:
     * 使用dfs，四个方向进行搜索。
     * ？
     * 这个和backtracking有什么区别呢？？
     * @param grid
     * @return
     */
    // time: O(m * n) space: O(n)
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null ||  grid.length == 0) return res;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }

}
