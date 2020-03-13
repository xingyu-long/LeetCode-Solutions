package com.leetcode.dynamicProgramming;

public class _123_BestTimetoBuyandSellStockIII {

    /**
     * Time: 11/6/2019, 03/12/2019
     * @param prices
     * @return
     */
    // https://www.youtube.com/watch?v=oDhu5uGq_ic
    // time:O(kNM) space:O(m*n)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int t = 2;
        int days = prices.length;
        int[][] dp = new int[t + 1][days];
        for (int i = 1; i < t + 1; i++) {
            for (int j = 1; j < days; j++) {
                int max = 0;
                for (int k = 0; k < j; k++) {
                    max = Math.max(max, prices[j] - prices[k] + dp[i - 1][k]);
                }
                dp[i][j] = Math.max(max, dp[i][j - 1]);
            }
        }
        return dp[t][days - 1];
    }

    // O(M*N) space:O(M*N)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
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
}
