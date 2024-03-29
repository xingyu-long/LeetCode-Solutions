package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _1155_NumberofDiceRollsWithTargetSum {

    // 这个其实就是backtracking里面常见的。
    // 这里的记忆化应该是用numOfDice以及target做。
    // time:O(d * target * f) space:O(d * target)
    public int numRollsToTarget(int d, int f, int target) {
        if (target == 0) return 1;
        int mod = (int) Math.pow(10, 9) + 7;
        int[][] memo = new int[d + 1][target + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(d, f, target, memo, mod) % mod;
    }

    public int dfs(int d, int f, int target, int[][] memo, int mod) {
        if (target == 0 && d == 0) return 1;
        if (target < 0 || d < 0) return 0;
        if (memo[d][target] != -1) return memo[d][target];
        int res = 0;
        for (int i = 1; i <= f; i++) {
            res += dfs(d - 1, f, target - i, memo, mod);
            res %= mod;
        }
        memo[d][target] = res;
        return res;
    }

    // 0/1背包问题
    // bottom up 三种循环，其实和那个coin change 2 差不多，但是这里相加也是dp[i-1][j - k]表示每个只能用一次 不是可以多个无限用。
    public int numRollsToTarget2(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        int mod = (int) Math.pow(10, 9) + 7;
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                for (int k = 1; k <= f && k <= j; k++) {
                    // try all possibilities
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] %= mod;
                }
            }
        }
        return dp[d][target];
    }
}
