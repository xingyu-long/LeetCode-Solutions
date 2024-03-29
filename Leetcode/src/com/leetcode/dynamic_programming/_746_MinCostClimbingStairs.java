/*
 * @Date: 02/02/2020 22:30:21
 * @LastEditTime: 06/07/2021 09:56:33
 * @Description: DP, DFS + memo
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _746_MinCostClimbingStairs {
    // time: O(n)
    public int minCostClimbingStairs(int[] cost) {
        // dfs + memo
        if (cost == null || cost.length == 0) return 0;
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
    
    // 倒着想
    public int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs2(cost, n, memo);
    }
    
    private int dfs2(int[] cost, int i, int[] memo) {
        if (i <= 1) return 0;
        if (memo[i] != -1) return memo[i];
        int res = Math.min(dfs2(cost, i - 1, memo) + cost[i - 1], dfs2(cost, i - 2, memo) + cost[i - 2]);
        memo[i] = res;
        return res;
    }

    // time:O(n) 就感觉是climbing stairs，直接从2开始计算。
    public int minCostClimbingStairs3(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        int[] dp = cost.clone();
        int n = dp.length;
        for (int i = 2; i < dp.length; i++) {
            dp[i] += Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
