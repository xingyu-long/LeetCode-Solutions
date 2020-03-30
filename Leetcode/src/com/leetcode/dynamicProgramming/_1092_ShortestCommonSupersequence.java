/*
 * @Date: 2020-03-29 15:21:56
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-29 15:24:00
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1092_ShortestCommonSupersequence {
    // 结合DP + pointer 还是比较有意思。
    // time: O(len(str1) * len(str2) + max(len(s1), len(s2)))
    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = commonSubSeq(str1, str2);
        int p1 = 0, p2 = 0;
        int n = lcs.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // 把之前和lcs不匹配的都加入结果集里
            while (p1 < str1.length() && str1.charAt(p1) != lcs.charAt(i)) {
                sb.append(str1.charAt(p1++));
            }
            while (p2 < str2.length() && str2.charAt(p2) != lcs.charAt(i)) {
                sb.append(str2.charAt(p2++));
            }
            sb.append(lcs.charAt(i));
            p1++;
            p2++;
        }
        sb.append(str1.substring(p1)).append(str2.substring(p2));
        return sb.toString();
    }
    
    public String commonSubSeq(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        String[][] dp = new String[m + 1][n + 1];
        for (String[] arr : dp) {
            Arrays.fill(arr, "");
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}