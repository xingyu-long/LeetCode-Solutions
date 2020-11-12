package com.leetcode.greedy;

/**
 * @Date: 09/27/2020
 * @Description: Simulation, Greedy
 **/
public class _1599_MaximumProfitofOperatingaCentennialWheel {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        // 应该用sum来判断就好了，以及没有看懂那个运算的情况应该就是累加
        int sum = 0;
        int run = 0, maxRun = 0;
        int profit = 0, maxProfit = 0;
        int i = 0, n = customers.length;
        while (sum > 0 || i < n) {
            if (i < n) sum += customers[i++];
            int numIn = Math.min(sum, 4);
            sum -= numIn;
            profit += boardingCost * numIn - runningCost;
            run++;
            if (profit > maxProfit) {
                maxProfit = profit;
                maxRun = run;
            }
        }
        return maxProfit > 0 ? maxRun : -1;
    }
}
