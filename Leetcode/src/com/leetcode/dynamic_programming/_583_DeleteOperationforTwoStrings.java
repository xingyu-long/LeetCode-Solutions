/*
 * @Date: 03/25/2020 18:14:09
 * @LastEditTime: 05/07/2021 09:27:10
 * @Description: DP
 */
package com.leetcode.dynamic_programming;

public class _583_DeleteOperationforTwoStrings {
    // time: O(len(w1) * len(w2))
    public int minDistance(String word1, String word2) {
        /*
            '' e a t 
         '' 0  1 2 3
         s  1  2 3 4
         e  2  1 2 3
         a  3  2 1 2
        */
        // dp[i][j] 需要多少步让 i + 1, j + 1的string相同。
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 两边分别验证
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
    
    
    // 可以先算出longest common subsequence 然后分别减去即可 
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return len1 + len2 - dp[len1][len2] * 2;
    }
}