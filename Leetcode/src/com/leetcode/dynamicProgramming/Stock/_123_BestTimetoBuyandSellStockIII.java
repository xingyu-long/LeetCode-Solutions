package com.leetcode.dynamicProgramming.Stock;

/**
 * @Date: 11/6/2019, 03/12/2019, 05/03/2020
 * @Description: DP, Stock
 **/
public class _123_BestTimetoBuyandSellStockIII {

    // https://www.youtube.com/watch?v=oDhu5uGq_ic
    // time:O(kNM) space:O(m*n)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int t = 2;
        int days = prices.length;
        int[][] dp = new int[t + 1][days];
        for (int i = 1; i < t + 1; i++) {
            for (int j = 1; j < days; j++) {
                int max = 0;
                // 第j天卖掉第k天买来的股票
                for (int k = 0; k < j; k++) {
                    max = Math.max(max, prices[j] - prices[k] + dp[i - 1][k]);
                }
                // dp[i][j - 1]表示第j天没有交易
                dp[i][j] = Math.max(max, dp[i][j - 1]);
            }
        }
        return dp[t][days - 1];
    }

    // O(M*N) space:O(M*N)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int m = 2;
        int n = prices.length;
        int[][] dp = new int[m + 1][n];
        for (int i = 1; i <= m; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[m][n - 1];
    }

    // 更加的优化，表示buy first, sell first, buy second, sell second.
    // time:O(n)
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int buyFirst = Integer.MIN_VALUE, buySecond = Integer.MIN_VALUE; // 表示买了当前之后剩下的钱
        int sellFirst = 0, sellSecond = 0; // 表示卖出之后的钱。
        for (int price : prices) {
            buyFirst = Math.max(buyFirst, -price);
            sellFirst = Math.max(sellFirst, buyFirst + price);
            buySecond = Math.max(buySecond, sellFirst - price);
            sellSecond = Math.max(sellSecond, buySecond + price);
        }
        return sellSecond;
    }

    public int maxProfit4(int[] prices) {
        // 最多两次transition
        // 如何表示 已经完成了一次transition？？？
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0]; // 相当于可能会在buyTwo这个位置进行交易
        int res = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            res = Math.max(res, dp[i][1]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
            res = Math.max(res, dp[i][3]);
        }
        return res;
    }
}
