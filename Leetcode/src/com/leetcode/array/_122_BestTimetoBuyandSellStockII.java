package com.leetcode.array;

public class _122_BestTimetoBuyandSellStockII {

    /**
     * 122. Best Time to Buy and Sell Stock II
     * when: 2019/03/21, 07/08/2019, 11/06/2019
     * 03/14/2020
     * solution :
     * (1) Peak Valley 方法：相当于寻找每一个连续的valley和peak 然后算出profit再加在一起
     * (2) 跟上面其实一样，只需要关注连续的部分即可
     *
     * @param prices
     * @return
     */
    // eg: 7, 1, 5, 3, 6, 4 这里的最后走到4这个位置，valley和peak想通过所以直接结果加上0。
    // time:O(n) space:O(1)
    public int maxProfit(int[] prices) {

        // solution 1:
        // 判断边界条件
        if (prices == null || prices.length < 2) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit = 0;
        while (i < prices.length) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxProfit += peak - valley;
            i++;
        }
        return maxProfit;
    }

    // time:O(n) space:O(1)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
