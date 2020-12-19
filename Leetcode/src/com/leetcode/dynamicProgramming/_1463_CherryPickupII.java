/*
 * @Date: 12/19/2020 12:03:24
 * @LastEditTime: 12/19/2020 12:05:26
 * @Description: DP + memo
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1463_CherryPickupII {
    // time: O(m * n^2 * 9)
    // space: O(m * n^2)
    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[n][n][m];
        for (int[][] arr : memo) {
            for (int[] temp : arr) {
                Arrays.fill(temp, Integer.MIN_VALUE);
            }
        }
        // 从上到下出发，并且每次每个点会被加一次，所以不用visit数组。
        return dfs(grid, 0, n - 1, 0, memo);
    }

    private int dfs(int[][] grid, int y1, int y2, int x, int[][][] memo) {
        if (y1 < 0 || y1 >= grid[0].length || y2 < 0 || y2 >= grid[0].length || x >= grid.length) {
            return 0;
        }
        if (memo[y1][y2][x] != Integer.MIN_VALUE) {
            return memo[y1][y2][x];
        }
        int curr = grid[x][y1];
        if (y1 != y2) {
            curr += grid[x][y2];
        }
        int res = 0;
        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                res = Math.max(res, curr + dfs(grid, y1 + d1, y2 + d2, x + 1, memo));
            }
        }
        memo[y1][y2][x] = res;
        return res;
    }
}
