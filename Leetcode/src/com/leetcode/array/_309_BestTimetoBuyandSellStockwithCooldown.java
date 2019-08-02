package com.leetcode.array;

public class _309_BestTimetoBuyandSellStockwithCooldown {
    /**
     *   309. Best Time to Buy and Sell Stock with Cooldown
     *   When:2019/8/2
     *   Difficulty: Medium

         感觉很难。。。
        hold[i] = max(hold[i - 1], res[i - 1] - price[i])
        sold[i] = hold[i - 1] + price[i]
        rest[i] = max(rest[i - 1], sold[i - 1])
     */
    //  https://www.youtube.com/watch?v=oL6mRyTn56M
    // 三种状态转换
    public int maxProfit(int[] prices) {
        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        for(int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(rest, sold);
    }
}