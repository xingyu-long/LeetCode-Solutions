/*
 * @Date: 03/20/2020 21:09:51
 * @LastEditTime: 02/18/2021 09:33:31
 * @Description: DP
 */

package com.leetcode.dynamicProgramming;

public class _413_ArithmeticSlices {
    // 需要多思考，其中的关系
    // 记住这里的关系，是“连续的”才行，不用考虑间隔的情况
    // 又想成了往回看dp。。
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
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
