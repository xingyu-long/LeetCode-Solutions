package com.leetcode.dynamic_programming.min_max;

/**
 * @Date: 04/10/2020
 * @Description: DP, MinMax
 **/
public class _375_GuessNumberHigherorLowerII {

    /**
     * state: memo[i][j] 表示从1到n确保能够win给出的代价是多少
     * init: None
     * func: i <= k <= j: min(k + max(dfs(i, k -1), dfs(k + 1, j)))
     * res: memo[1][n]
     * @param n
     * @return
     */
    // time: O(n^3) 因为需要i, j, x三个循环 space:O(n^2)
    public int getMoneyAmount(int n) {
        int[][] memo;
        memo = new int[n + 1][n + 1];
        return helper(1, n, memo);
    }

    // 带有memo Top-down
    private int helper(int i, int j, int[][] memo) {
        if (i >= j) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            // 这里说需要保证，那么就需要在猜的左右情况取最差，即最大的情况。然后取出最小
            // 每次做了选择之后，会选其中一边，因为题意的要求。
            res = Math.min(res, x + Math.max(helper(i, x - 1, memo), helper(x + 1, j, memo)));
        }
        memo[i][j] = res;
        return res;
    }

    // 利用DP bottom-up 相当于把每个分段的值计算好
    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = i; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], x +
                        Math.max(dp[i][x - 1], dp[x + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
