/*
 * @Date: 12/19/2020 11:34:52
 * @LastEditTime: 12/19/2020 11:36:56
 * @Description: DFS + memo
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _741_CherryPickup {
    // time: O(N^3) space: O(N^3)
    public int cherryPickup(int[][] grid) {
        // 利用huhaua的解法比较直接，想象成两个人一起走到原点。
        int n = grid.length;
        int[][][] memo = new int[n + 1][n + 1][n + 1];
        for (int[][] arr : memo) {
            for (int[] temp : arr) {
                Arrays.fill(temp, Integer.MIN_VALUE);
            }
        }

        return Math.max(0, dfs(grid, n - 1, n - 1, n - 1, memo));
    }

    private int dfs(int[][] grid, int x1, int y1, int x2, int[][][] memo) {
        int y2 = x1 + y1 - x2; // 因为都是向上或者向左移动，x + y是个定值
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0)
            return -1;
        if (grid[x1][y1] == -1 || grid[x2][y2] == -1)
            return -1;
        if (x1 == 0 && y1 == 0)
            return grid[x1][y1]; // 默认用x1,y1来获取最后的结果
        if (memo[x1][y1][x2] != Integer.MIN_VALUE)
            return memo[x1][y1][x2];
        int res = Math.max(
                Math.max(dfs(grid, x1 - 1, y1, x2, memo),
                        Math.max(dfs(grid, x1 - 1, y1, x2 - 1, memo), dfs(grid, x1, y1 - 1, x2, memo))),
                dfs(grid, x1, y1 - 1, x2 - 1, memo));
        if (res < 0) {
            memo[x1][y1][x2] = -1; // cannot not achieved.
            return -1;
        }
        res += grid[x1][y1];
        if (x1 != x2)
            res += grid[x2][y2];

        memo[x1][y1][x2] = res;
        return res;
    }
}
