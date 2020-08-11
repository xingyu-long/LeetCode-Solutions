package com.leetcode.dynamicProgramming;

/**
 * @Date: 07/18/2020
 * @Description: DP, min.
 **/
public class _265_PaintHouseII {
    // brute force
    // time:O(m * n * n)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int m = costs.length, n = costs[0].length;
        // 每次保留一个最小值？
        // n*k^2
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j == k) continue;
                    min = Math.min(min, costs[i - 1][k]);
                }
                costs[i][j] += min;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, costs[m - 1][j]);
        }
        return res;
    }
}
