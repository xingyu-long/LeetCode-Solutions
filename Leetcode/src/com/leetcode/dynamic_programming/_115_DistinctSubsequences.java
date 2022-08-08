package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 04/14/2020
 * @Description: 双字符串DP
 **/
public class _115_DistinctSubsequences {

    // time:O(m * n) space:O(m * n)
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // 初始化条件 第0列应该均为1
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    // top-down + memo
    public int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(s, t, m, n, memo);
    }

    // 越界的问题 i j分别设置为len才对
    public int dfs(String s, String t, int i, int j, int[][] memo) {
        // base case
        if (j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            res = dfs(s, t, i - 1, j - 1, memo) + dfs(s, t, i - 1, j, memo);
        } else {
            res = dfs(s, t, i - 1, j, memo);
        }
        memo[i][j] = res;
        return res;
    }
}