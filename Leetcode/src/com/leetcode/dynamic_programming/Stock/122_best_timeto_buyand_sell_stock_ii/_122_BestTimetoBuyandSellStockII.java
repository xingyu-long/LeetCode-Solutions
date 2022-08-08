package com.leetcode.dynamic_programming.Stock;

/**
 * @Date: 2019/03/21, 07/08/2019, 11/06/2019, 03/14/2020, 04/05/2020, 05/03/2020
 * @Description: DP, Stock
 **/
public class _122_BestTimetoBuyandSellStockII {

    // (1) Peak Valley 方法：相当于寻找每一个连续的valley和peak 然后算出profit再加在一起
    // (2) 动态规划的方法更加的直接，并且更加广泛的使用
    // eg: 7, 1, 5, 3, 6, 4 这里的最后走到4这个位置，valley和peak想通过所以直接结果加上0。
    // time:O(n) space:O(1)
    public int maxProfit(int[] prices) {

        // solution 1:
        // 判断边界条件
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit = 0;
        while (i < prices.length) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
            i++;
        }
        return maxProfit;
    }

    /**
     * 画图 i - 1   Buy    Sell i      Buy    Sell 当前的Buy可以来自上一层的sell然后我们买当前的，或者是这层从上一层的buy状态延续下来，表示啥也没做。sell同样分析
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        // 0 buy
        // 1 sell
        int res = 0;
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);//buy
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);//sell
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    // 改为滚动数组，但是需要记录以前的一个值，这样才有效。
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int n = prices.length;
        int buy = 0, sell = 0;
        buy -= prices[0];
        for (int i = 1; i < n; i++) {
            int prevBuy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, prevBuy + prices[i]);
            res = Math.max(res, sell);
        }
        return res;
    }
}
