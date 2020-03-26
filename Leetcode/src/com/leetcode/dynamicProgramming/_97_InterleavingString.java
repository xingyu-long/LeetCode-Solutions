/*
 * @Date: 2019-08-01 07:12:49
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-23 18:06:27
 */
package com.leetcode.dynamicProgramming;

public class _97_InterleavingString {
    // time:O(m * n) space:O(m * n)
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // init first row and col;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int index = i + j - 1; // for s3;
                char ch = s3.charAt(index);
                // 可能来自的两个方向。
                if (ch == s1.charAt(i - 1) || ch == s2.charAt(j - 1)) {
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == ch) || 
                        (dp[i - 1][j] && s1.charAt(i - 1) == ch);
                }
            }
        }
        return dp[m][n];
    }
}
