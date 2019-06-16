package com.leetcode.array;

public class _121_BestTimetoBuyandSellStock {

    /**
     * 121. Best Time to Buy and Sell Stock
     * when: 2019/03/21
     *
     * solution: 利用min以及profit 初始化，然后找到最小的min
     * 并且跟着算最大的profit以之前的profit比较
     * 这个思路还是很值得考虑
     *
     *
     * Say you have an array for which the i^th element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * example:
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     *
     * @param prices
     * @return
     */
    // time:O(n) space:O(1)
    public int maxProfit(int[] prices) {
        if (prices.length < 2 || prices == null) return 0;
        int min = prices[0];
        int profit = 0;
        for (int price: prices){
            min =  Math.min(price, min);
            profit = Math.max(price - min, profit);
        }
        return profit;
    }
}
