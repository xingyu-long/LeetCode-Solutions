/*
 * @Date: 2020-03-26 14:53:08
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-26 14:55:01
 */
package com.leetcode.dynamic_programming;

public class _712_MinimumASCIIDeleteSumforTwoStrings {
    // 和583基本一样
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] += dp[i - 1][0] + (int) s1.charAt(i - 1);
        }
        
        for (int j = 1; j <= n; j++) {
            dp[0][j] += dp[0][j - 1] + (int) s2.charAt(j - 1);
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + (int) s1.charAt(i - 1));
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + (int) s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }
    
}