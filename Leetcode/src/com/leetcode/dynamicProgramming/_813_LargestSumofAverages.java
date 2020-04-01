/*
 * @Date: 2020-03-31 18:03:26
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-31 18:05:16
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _813_LargestSumofAverages {

    // 分组DP，相同的右1278, 410
    // 求和那部分还可以优化
    // time: O(k * n * n * sum) space: O(n * k)
    public double largestSumOfAverages(int[] A, int K) {
        if (A == null || A.length == 0) return 0.0;
        int n = A.length;
        double[][] memo = new double[n + 1][K + 1];
        for (double[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(A, A.length - 1, K, memo);
    }
    
    public double dfs(int[] A, int j, int k, double[][] memo) {
        if (memo[j][k] != -1) return memo[j][k];
        if (k == 1) return 1.0 * sum(A, 0, j) / (j + 1);
        double res = 0.0;
        for (int i = j - 1; i >= 0; i--) {
            res = Math.max(res, dfs(A, i, k - 1, memo) + 1.0 * sum(A, i + 1, j) / (j - i));
        }
        memo[j][k] = res;
        return res;
    }
    
    public int sum (int[] nums, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }
}