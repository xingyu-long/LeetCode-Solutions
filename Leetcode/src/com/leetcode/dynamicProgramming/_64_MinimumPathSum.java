package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _64_MinimumPathSum {

    /**
     *  64. Minimum Path Sum
     *  When: 2019/05/08
     *  Review1:2019/8/1
     *  Difficulty: Medium
     *
     *  dp[i][j] 表示到这个点所经历的路线和是多少
     *  状态转移方程: 由于只能向右或者向下，先初始化第一行和第一列，做累加操作
     *    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
     * @param grid
     * @return
     */
    // time: O(m * n) space: O(1)(没有新建二维数组)
    public int minPathSum(int[][] grid) {
        //主体思路和之前差不多，就是计算第一行和第一列的，但这里是一个累和的过程
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 越界问题呢？ 因为可能执行grid[0][0]这个点 所以没事
                if (i == 0 && j != 0) grid[i][j] += grid[i][j - 1]; // 第一行
                if (i != 0 && j == 0) grid[i][j] += grid[i-1][j]; // 第一列
                if (i != 0 && j != 0) {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    // 求路径也可以。
    static String miniPath = "";
    static int res = Integer.MAX_VALUE;
    public static int minPathSum2(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        dfs(grid, 0, 0, "", 0, visited);
        System.out.println("path = " + miniPath);
        return res;
    }

    public static void dfs(int[][] grid, int row, int col, String path, int sum, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) return;
        if (visited[row][col]) return;
        path += grid[row][col] + (row == grid.length - 1 && col == grid[0].length - 1 ? "" : "->");
        sum += grid[row][col];
        visited[row][col] = true;
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            if (sum < res) {
                res = sum;
                miniPath = path;
            }
        }
        dfs(grid, row, col + 1, path, sum, visited);
        dfs(grid, row + 1, col, path, sum, visited);
        visited[row][col] = false;
    }

    // 利用dfs + memo
    // 填充最后一行最后一列的那个右边和下边为0，这样就可以返回min。
    public int minPathSum3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) Arrays.fill(row, Integer.MAX_VALUE);
        memo[m][n - 1] = memo[m - 1][n] = 0;
        return dfs(grid, 0, 0, memo);
    }

    public int dfs(int[][] grid, int i, int j, int[][] memo) {
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return Integer.MAX_VALUE;

        int res = 0;
        res += Math.min(dfs(grid, i + 1, j, memo), dfs(grid, i, j + 1, memo)) + grid[i][j];
        memo[i][j] = res;
        return res;
    }
}
