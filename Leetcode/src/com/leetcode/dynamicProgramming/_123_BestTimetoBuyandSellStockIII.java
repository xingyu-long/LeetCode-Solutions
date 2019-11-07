package com.leetcode.dynamicProgramming;

public class _123_BestTimetoBuyandSellStockIII {

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Time: 11/6/2019
     * Difficulty: Hard
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
}
