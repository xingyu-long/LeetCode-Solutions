package com.leetcode.dynamicProgramming;

/**
 * @Date: 07/18/2020
 * @Description: 时间顺序DP
 **/
public class _256_PaintHouse {

    // time:O(n) space:O(1)
    // 考虑的时候应该是当前层以及看前面这样来想这个题。
    public int minCost(int[][] costs) {
        if (costs == null || costs[0].length == 0) {
            return 0;
        }
        for (int i = 1; i < costs.length; i++) {
            //前两个的最小
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][2], costs[i - 1][0]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        int n = costs.length - 1;
        return Math.min(costs[n][0], Math.min(costs[n][1], costs[n][2])); //比较最后的方案
    }
}
