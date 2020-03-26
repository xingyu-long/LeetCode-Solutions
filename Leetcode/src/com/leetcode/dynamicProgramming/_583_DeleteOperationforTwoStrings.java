/*
 * @Date: 2020-03-25 18:14:09
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-25 18:14:52
 */
package com.leetcode.dynamicProgramming;

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
    
}