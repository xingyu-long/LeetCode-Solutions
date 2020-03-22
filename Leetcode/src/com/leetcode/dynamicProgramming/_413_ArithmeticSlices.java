/*
 * @Date: 2020-03-20 21:09:51
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-20 21:10:47
 */
package com.leetcode.dynamicProgramming;

public class _413_ArithmeticSlices {
    // 需要多思考，其中的关系
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if (A == null || n == 0) return 0;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }
}
