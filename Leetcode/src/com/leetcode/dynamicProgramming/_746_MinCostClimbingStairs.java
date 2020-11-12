package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _746_MinCostClimbingStairs {
    // 746. Min Cost Climbing Stairs
    // time: O(n)
    public int minCostClimbingStairs(int[] cost) {
        // dfs + memo
        if (cost == null || cost.length == 0) return 0;
        int res = Integer.MAX_VALUE;
        int[] memo = new int[cost.length + 1];
        Arrays.fill(memo, -1);
        return Math.min(dfs(cost, 0, memo), dfs(cost, 1, memo));
    }

    public int dfs(int[] cost, int index, int[] memo) {
        if (index >= cost.length) return 0;
        if (memo[index] != -1) return memo[index];
        int res = Integer.MAX_VALUE;
        res = Math.min(res, dfs(cost, index + 1, memo) + cost[index]);
        res = Math.min(res, dfs(cost, index + 2, memo) + cost[index]);
        memo[index] = res;
        return res;
    }

    // time:O(n) 就感觉是climbing stairs，直接从2开始计算。
    public int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        int[] dp = cost.clone();
        int n = dp.length;
        for (int i = 2; i < dp.length; i++) {
            dp[i] += Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
