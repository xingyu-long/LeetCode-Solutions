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

    // time: O(n + nlogn)
    // space: O(n)
    // https://leetcode.com/problems/two-city-scheduling/discuss/667786/Java-or-C%2B%2B-or-Python3-or-With-detailed-explanation
    public int twoCitySchedCost2(int[][] costs) {
        int n = costs.length;
        // 如果全都选择走去a
        int totalCostForA = 0;
        int[] refund = new int[n];
        for (int i = 0; i < n; i++) {
            totalCostForA += costs[i][0];
            // 到达b和到达a所需要花费的差值
            refund[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(refund);
        int res = totalCostForA;
        // 选择其中的一半最小的差值进行refund
        for (int i = 0; i < n / 2; i++) {
            res += refund[i];
        }
        return res;
    }
}
