package com.leetcode.array;

public class _121_BestTimetoBuyandSellStock {

    /**
     *  121. Best Time to Buy and Sell Stock
     *  when: 2019/03/21
     *  Review: 2019/6/16
        Difficulty: Easy
        review2: 2019/8/21

        solution:
        利用min以及profit 初始化，然后找到最小的min
        并且跟着算最大的profit以之前的profit比较
        这个思路还是很值得考虑
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
