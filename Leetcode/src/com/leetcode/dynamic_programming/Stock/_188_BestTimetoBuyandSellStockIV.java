package com.leetcode.dynamic_programming.Stock;

/**
 * @Date: 05/06/2020
 * @Description: DP, stock
 **/
public class _188_BestTimetoBuyandSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) {
            return 0;
        }
        int days = prices.length;
        if (k >= days) { // 这里没写return
            return maxProfitWithGreaterK(prices);
        }
        int[][] dp = new int[k + 1][days];
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < days; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], maxDiff + prices[j]);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][days - 1];
    }

    private int maxProfitWithGreaterK(int[] nums) {
        int n = nums.length;
        int buy = -nums[0];
        int sell = 0;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int prevBuy = buy;
            buy = Math.max(buy, sell - nums[i]);
            sell = Math.max(sell, prevBuy + nums[i]);
            res = Math.max(res, sell);
        }
        return res;
    }
}
