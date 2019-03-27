package com.leetcode;

public class _122_BestTimetoBuyandSellStockII {

    /**
     * 122. Best Time to Buy and Sell Stock II
     * when: 2019/03/21
     *
     * solution1 :
     * Peak Valley 方法：相当于寻找每一个连续的valley和peak 然后算出profit再加在一起
     * solution2:
     * 跟上面其实一样，只需要关注连续的部分即可
     *
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     *
     * Example:
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     *
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
//     solution 1:
//      判断边界条件
        if (prices == null || prices.length < 2) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) { //这里是因为后面的操作总是会有i+1
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;


        /**
         * solution2:
         * if (prices == null || prices.length < 2) return 0;
         * int maxprofit = 0
         * for (int i = 1; i < prices.length; i++){
         *      if(prices[i] > prices[i - 1])
         *          maxprofit += prices[i] - prices[i - 1];
         * }
         * return maxprofit;
         */
    }
}
