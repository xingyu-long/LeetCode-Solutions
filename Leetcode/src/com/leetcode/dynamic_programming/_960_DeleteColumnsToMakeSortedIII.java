/*
 * @Date: 01/07/2021 11:17:44
 * @LastEditTime: 01/07/2021 11:18:27
 * @Description: LIS dp
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _960_DeleteColumnsToMakeSortedIII {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length();
        int res = n - 1, k = 0;
        int[] dp = new int[n]; // 代表当前列截止最大的LIS
        Arrays.fill(dp, 1);
        for (int j = 0; j < n; j++) { // 往回看 [i,j] 是不是LIS
            for (int i = 0; i < j; i++) {
                for (k = 0; k < m; k++) {
                    if (A[k].charAt(i) > A[k].charAt(j)) {
                        break;
                    }
                }
                if (k == m) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            res = Math.min(res, n - dp[j]);
        }
        return res;
    }
}
