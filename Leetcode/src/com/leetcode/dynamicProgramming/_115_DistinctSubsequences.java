/*
 * @Date: 2020-03-25 11:33:18
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-25 11:33:35
 */
package com.leetcode.dynamicProgramming;

public class _115_DistinctSubsequences {
    public int numDistinct(String s, String t) {
        // 相同的时候 转移方程自己没有定义好
        // 更新顺序应该是 以t为row s为col，这样才能表示每次移动s中的字符
        int lenT = t.length();
        int lenS = s.length();
        int[][] dp = new int[lenT + 1][lenS + 1];
        // S中任意字符都能构成空字符
        for (int j = 0; j <= lenS; j++) {
            dp[0][j] = 1;
        }
        
        for (int i = 1; i <= lenT; i++) {
            for (int j = 1; j <= lenS; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1]; // 不相同的话就是看前面的构成情况
                }
            }
        }
        return dp[lenT][lenS];
    }
    
}