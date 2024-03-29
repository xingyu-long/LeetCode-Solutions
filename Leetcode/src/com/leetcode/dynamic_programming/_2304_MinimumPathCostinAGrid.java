/*
 * @Date: 07/22/2022 21:24:41
 * @LastEditTime: 07/22/2022 21:24:51
 * @Description: Sequence DP
 */

package com.leetcode.dynamic_programming;

public class _2304_MinimumPathCostinAGrid {
    // time: O(m * n * n)
    // space: O(m * n)
    public int minPathCost(int[][] grid, int[][] moveCost) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // comes from prev level
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    int value = grid[i - 1][k];
                    min = Math.min(min, dp[i - 1][k] + moveCost[value][j]);
                }
                dp[i][j] = min + grid[i][j];
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp[m - 1][j]);
        }
        return res;
    }
}