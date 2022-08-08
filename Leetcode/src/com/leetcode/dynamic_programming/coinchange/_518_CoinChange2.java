package com.leetcode.dynamic_programming.coinchange;

import java.util.Arrays;

public class _518_CoinChange2 {
    /**
     *
     * 518. Coin Change 2
     * When:10/5/2019, 02/27/2020
     * 状态转移方程
     * table[row][col] = table[row][col - coins[row - 1]](use) + table[row - 1][col](not use)
     * table[row][col] 指当前amount下，利用不同coin的总方法数
     * @param amount
     * @param coins
     * @return
     */
    // time:O(m * n) m -> amount; n -> coins.length
    // space:O(m * n)
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1; // 表示amount为0的情况下，有各种面额配对的情况。只能什么都不做，也是种选择。
            for (int j = 1; j <= amount; j++) {
                int currentCoin = coins[i - 1];
                int notUseCoin = dp[i - 1][j];
                int useCoin = (j >= currentCoin) ? dp[i][j - currentCoin] : 0;
                dp[i][j] = notUseCoin + useCoin;
            }
        }
        return dp[coins.length][amount];
    }
    // time: O(m * n) space:O(m) 画表即可，思路和上面一致
    // 注意i是从每次的coin开始循环
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) { // 从coin开始遍历
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


    // dfs + memo
    // time:O(m * n) space:O(m*n)
    public int change3(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins == null || coins.length == 0) return 0;
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(amount, coins, coins.length - 1, memo);
    }

    public int dfs(int amount, int[] coins, int choice, int[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        if (choice < 0) return 0;
        if (memo[choice][amount] != -1) return memo[choice][amount];
        int res = 0;
        // 用还是不用。
        // 用
        res += dfs(amount - coins[choice], coins, choice, memo);
        // 不用
        res += dfs(amount, coins, choice - 1, memo);

        memo[choice][amount] = res;
        return res;
    }
}
