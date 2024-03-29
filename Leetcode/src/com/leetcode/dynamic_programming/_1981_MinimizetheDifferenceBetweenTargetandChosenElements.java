package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _1981_MinimizetheDifferenceBetweenTargetandChosenElements {
    class Solution {
        // 利用top down + memo
        // time: O(m * n * (sums))
        public int minimizeTheDifference(int[][] mat, int target) {
            int[][] dp = new int[mat.length][5000];
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            return dfs(mat, 0, 0, target, dp);
        }

        public int dfs(int[][] mat, int sum, int row, int target, int[][] dp) {
            if (row == mat.length) {
                return Math.abs(sum - target);
            }
            if (dp[row][sum] != Integer.MAX_VALUE) {
                return dp[row][sum];
            }

            int n = mat[row].length;
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                res = Math.min(res, dfs(mat, sum + mat[row][j], row + 1, target, dp));
            }
            dp[row][sum] = res;
            return res;
        }
    }
}
