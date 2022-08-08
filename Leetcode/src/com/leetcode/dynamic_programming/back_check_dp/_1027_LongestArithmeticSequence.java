package com.leetcode.dynamic_programming.back_check_dp;

/**
 * @Date: 04/25/2020
 * @Description: 向回看DP
 **/
public class _1027_LongestArithmeticSequence {

    // 类似于LIS问题。
    // time:O(n^2) space:O(n^2) 去计算所有可能存在的diff的情况，并且看上一层是否有这种情况。
    public int longestArithSeqLength(int[] A) {
        // 构建相同的递增或递减序列
        if (A == null || A.length == 0) {
            return 0;
        }
        int val = 10001;
        int n = A.length;
        int[][] dp = new int[n][2 * val];
        int res = 0;
        // 向回看DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j] + val;
                dp[i][diff] = 1; // 当前的这一个开始新建
                dp[i][diff] = Math.max(dp[j][diff], dp[i][diff]) + 1;
                res = Math.max(res, dp[i][diff]);
            }
        }
        return res;
    }
}
