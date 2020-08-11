package com.leetcode.greedy;

import java.util.Arrays;

/**
 * @Date: 06/03/2020
 * @Description: greedy, DP
 **/
public class _1029_TwoCityScheduling {
    // time:O(nlogn) space:O(1)
    // https://www.cnblogs.com/xiaochuan94/p/11186875.html
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> ((a[0] - a[1]) - (b[0] - b[1])));
        int sum = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                sum += costs[i][0];
            } else {
                sum += costs[i][1];
            }
        }
        return sum;
    }

    // TODO: dp approach
}
