package com.leetcode.dynamic_programming;

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

    // 利用保留次小值的情况
    // time:O(m * n)
    public int minCostII2(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int m = costs.length, n = costs[0].length;
        int min1 = -1, min2 = -1;

        for (int i = 0; i < m; i++) {
            int last1= min1, last2 = min2;

            min1 = -1;
            min2 = -1;

            for (int j = 0; j < n; j++) {
                if (j != last1) { // 走第一行的时候，只会把值累加，因为当前的last1和last2都是-1。
                    costs[i][j] += (last1 >= 0 ? costs[i - 1][last1] : 0);
                } else {
                    costs[i][j] += (last2 >= 0 ? costs[i - 1][last2] : 0);
                }

                // 每次找寻最小值的index以及次小值的index。
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[m - 1][min1];
    }
}
