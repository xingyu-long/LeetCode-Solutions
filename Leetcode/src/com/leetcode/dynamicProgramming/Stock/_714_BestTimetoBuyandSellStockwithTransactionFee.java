/*
 * @Date: 03/14/2020 20:34:59
 * @LastEditTime: 03/16/2021 09:16:52
 * @Description: Stock DP
 */
package com.leetcode.dynamicProgramming.Stock;

public class _714_BestTimetoBuyandSellStockwithTransactionFee {

    // 股票问题，看起来像是可以连续的求解，直接用前面已经改变过的sell或者buy值？
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!
    // 类似于II的情况。
    // time:O(n) space:O(n)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
            // 这里确实是要把price加起来，因为你buy的时候已经用过了，现在是sell，所以是赚钱把sell的钱加回来。
        }
        return sell[n - 1];
    }
}