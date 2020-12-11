/*
 * @Date: 03/20/2020 21:09:51
 * @LastEditTime: 12/10/2020 11:06:50
 * @Description: DP
 */

package com.leetcode.dynamicProgramming;

public class _413_ArithmeticSlices {
    // 需要多思考，其中的关系
    // 记住这里的关系，是连续的才行，不用考虑间隔的情况
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
