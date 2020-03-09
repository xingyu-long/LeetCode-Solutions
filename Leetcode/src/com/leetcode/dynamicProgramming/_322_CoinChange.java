package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _322_CoinChange {
    /**
     *  322. Coin Change
     *  When:7/30/2019, 10/4/2019, 02/27/2020
     *  Difficulty: Medium
     *  solution: 每次计算的次数就是 （金额 - 当前数组的存在）的最好的情况 + 1（当前数组值代表的一张）
     *  DP[x] 代表金额为x的情况下最少的张数。
     * @param coins
     * @param amount
     * @return
     */
    //相当于是我采取当前面值币之后，寻求sum-当前币的最优情况
    // time:O(m * n) space:O(m)
    public int coinChange(int[] coins, int amount) {
        // 这里的递推关系是 计算 1 ~ amount每个的最小结果。并且需要钱每次大于coins[i]才能开始计算
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    min = Math.min(min, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }

    // dfs + memo
    public int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(coins, amount, memo);
    }

    public int dfs(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (memo[amount] != Integer.MAX_VALUE) return memo[amount];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] >= 0) {
                int res = dfs(coins, amount - coins[i], memo);
                // 这里处理的比较妙，先不要用比较后的值相加，这样防止了溢出的问题。
                if (res >= 0 && res < min) min = 1 + res;
            }
        }
        memo[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[amount]; // 这里是这个才对，不要直接返回min， min如果等于MAX的话，就会返回错误
    }
}
