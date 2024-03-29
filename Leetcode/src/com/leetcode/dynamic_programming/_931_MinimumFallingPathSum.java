package com.leetcode.dynamic_programming;

/**
 * @Date: 07/13/2020
 * @Description: dp
 **/
public class _931_MinimumFallingPathSum {
    // time:O(N^2) space:O(N^2)
    public int minFallingPathSum(int[][] A) {
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int m = A.length;
        int[][] dp = new int[m][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = A[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int min = Integer.MAX_VALUE;
                min = Math.min(min, dp[i - 1][j]);
                if (j == 0) {
                    min = Math.min(min, dp[i - 1][j + 1]);
                } else if (j == A[i].length - 1) {
                    min = Math.min(min, dp[i - 1][j - 1]);
                } else {
                    min = Math.min(min, Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]));
                }
                dp[i][j] =  A[i][j] + min;
            }
        }

        for (int j = 0; j < m; j++) {
            res = Math.min(res, dp[m - 1][j]);
        }
        return res;
    }
}
