package com.leetcode.dynamic_programming.Stock;

public class _309_BestTimetoBuyandSellStockwithCooldown {
    /**
     *   309. Best Time to Buy and Sell Stock with Cooldown
     *   When:2019/8/2
     *   Difficulty: Medium

         感觉很难。。。
        hold[i] = max(hold[i - 1], rest[i - 1] - price[i])
        sold[i] = hold[i - 1] + price[i]
        rest[i] = max(rest[i - 1], sold[i - 1])
     */
    //  https://www.youtube.com/watch?v=oL6mRyTn56M
    // 三种状态转换
    // 先开始sold是因为这样不会影响以前的值。
    public int maxProfit(int[] prices) {
        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        for(int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(rest, sold);
    }

    // 比较有规则
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]); //当前买，只能来自于cooldown或者是上一次的buy
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]); //当前卖
            res = Math.max(res, dp[i][1]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]); // 当前休息
            res = Math.max(res, dp[i][2]);
        }
        return res;
    }
}