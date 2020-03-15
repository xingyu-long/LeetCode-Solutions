package com.leetcode.dynamicProgramming;

public class _714_BestTimetoBuyandSellStockwithTransactionFee {
    
    /**
     * When:03/14/2020
     * @param prices
     * @param fee
     * @return
     */
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!
    // 类似于那个cool down以及III的情况。
    // time:O(n) space:O(n)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[n - 1];
    }
}