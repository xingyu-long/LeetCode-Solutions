package com.leetcode.dynamicProgramming;

public class _375_GuessNumberHigherorLowerII {
    // 这里说需要保证，那么就需要在猜的左右情况取最差，即最大的情况。然后取出最小
    // https://www.youtube.com/watch?v=VfJPDNG0nYM
    // 这个跟另外一个累计和的划分是相同的做法
    /**
     * 375. Guess Number Higher or Lower II
     * When:8/1/2019, 03/09/2020
     * solution: 相当于计算最差情况下的最小值。
     * Test case:
     * n = 5
     * 1, 2, 3, 4, 5
     * 进入for loop
     * 1 + max(helper(1, 0), helper(2, 5))
     * 2 + max(helper(1, 1), helper(3, 5))
     * 3 + max(helper(1, 2), helper(4, 5))
     * 4 + max(helper(1, 3), helper(5, 5))
     * 5 + max(helper(1, 4), helper(6, 5))
     * 然后依次计算得到
     * dp[i][j] 指 i~j的情况
     */
    // 分段DP题。和410基本差不多，410是求和的。
    // time: O(n^3) 因为需要i, j, x三个循环 space:O(n^2)
    int[][] memo;

    public int getMoneyAmount(int n) {
        memo = new int[n + 1][n + 1];
        return helper(1, n);
    }

    // 带有memo Top-down
    private int helper(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            // 每次做了选择之后，会选其中一边，因为题意的要求。
            res = Math.min(res, x + Math.max(helper(i, x - 1), helper(x + 1, j)));
        }
        memo[i][j] = res;
        return res;
    }

    // 利用DP bottom-up 相当于把每个分段的值计算好
    // 这些细节要好好考虑
    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = i; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], x +
                            Math.max(dp[i][x-1],dp[x+1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
