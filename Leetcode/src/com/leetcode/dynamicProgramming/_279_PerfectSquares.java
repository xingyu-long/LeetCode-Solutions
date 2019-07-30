package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _279_PerfectSquares {

    /**
     *  279. Perfect Squares
     *  When:2019/7/30
     *  Difficulty: Medium
     *
     * @param n
     * @return
     */
    // 其实这个跟coin change思路一样
    // time:O(n^2) space:O(n)
    public int numSquares(int n) {
        // 首先需要保存其完全平方数
        if (n <= 1) return n;
        int[] perfectNumbers = new int[n];
        perfectNumbers[0] = 1;
        for (int i = 1; i < n; i++) {
            perfectNumbers[i] = i * i;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (i - perfectNumbers[j] >= 0 && dp[i - perfectNumbers[j]] != -1) {
                    min = Math.min(min, dp[i - perfectNumbers[j]] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[n];
    }

    // 简化版
    // time:O(n * sqrt(n)) space:O(n)
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
