/*
 * @Date: 2020-03-31 18:03:26
 * @LastEditors: Clark long
 * @LastEditTime: 2020-04-02 18:14:22
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _813_LargestSumofAverages {

    // 分组DP，相同的右1278, 410
    // 求和那部分还可以优化
    // time: O(k * n * n * sum) space: O(n * k)
    public double largestSumOfAverages(int[] A, int K) {
        // 分组DP
        if (A == null || A.length == 0) return 0.0;
        int n = A.length;
        double[] prefix = new double[n];
        prefix[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + A[i];
        }
        double[][] memo = new double[n + 1][n + 1];
        for (double[] arr : memo) {
            Arrays.fill(arr, -1.0);
        }
        return dfs(A, prefix, K, n - 1, memo);
    }
    
    public double dfs(int[] A, double[] prefix, int k, int j, double[][] memo) {
        if (k == 1) {
            return prefix[j] / (j + 1); // 这个不要忘记！
        }
        if (memo[j][k] != -1) return memo[j][k];
        double res = Double.MIN_VALUE;
        for (int i = j - 1; i >= 0; i--) {
            res = Math.max(res, dfs(A, prefix, k - 1, i, memo) + (prefix[j]- prefix[i]) / (j - i));
        }
        memo[j][k] = res;
        return res;
    }
}