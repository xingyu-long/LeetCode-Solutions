/*
 * @Date: 2019-12-31 19:37:37
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-30 17:03:13
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    // DFS + memo
    // time:O(len(t1) * len(t2))
    // 倒着算，这样跟bottom up保持一致
    public int longestCommonSubsequence2(String text1, String text2) {
        // LCS problem
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(text1, text2, text1.length() - 1, text2.length() - 1, memo); 
    }
    
    public int dfs(String s, String t, int si, int ti, int[][] memo) {
        if (si < 0) return 0;
        if (ti < 0) return 0;
        if (memo[si][ti] != -1) return memo[si][ti];
        int res = 0;
        if (s.charAt(si) == t.charAt(ti)) {
            res = 1 + dfs(s, t, si - 1, ti - 1, memo);
        } else {
            res = Math.max(dfs(s, t, si, ti - 1, memo), dfs(s, t, si - 1, ti, memo));  
        }
        memo[si][ti] = res;
        return res;
    }
}
