package com.leetcode.dynamic_programming.Stock;

public class _121_BestTimetoBuyandSellStock {

    /**
     * When: 03/21/2019, 06/16/2019, 08/21/2019, 11/6/2019,
     * 03/14/2020
     * @param prices
     * @return
     */
    // time:O(n) space:O(1)
    public int maxProfit(final int[] prices) {
        if (prices.length < 2 || prices == null) return 0;
        int min = prices[0];
        int profit = 0;
        for (final int price : prices) {
            min = Math.min(price, min);
            profit = Math.max(price - min, profit);
        }
        return profit;
    }
}
