package com.leetcode.dynamicProgramming;

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
}
