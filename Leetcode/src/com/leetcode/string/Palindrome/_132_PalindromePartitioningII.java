/*
 * @Date: 2019-12-08 12:24:43
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-31 16:22:28
 */
package com.leetcode.string.Palindrome;

public class _132_PalindromePartitioningII {

    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        int[] cuts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = i;// 最多可以都cut，这样单独的字符肯定是palindrome
            for (int j = 1; j <= i; j++) {
                // 判断j~i是否为palindrome
                if (s.charAt(i - 1) == s.charAt(j - 1) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    // why  [j - 1] ? 因为这样才能保证最小 可以用例子 abb说明，当走到bb的时候j和i分别在一个b上
                    // cuts[j - 1] 代表前面j-1符合palindrome以及后面也是，这里需要割一刀 +1
                    min = (j == 1) ? 0 : Math.min(min, cuts[j - 1] + 1);
                }
            }
            cuts[i] = min;
        }
        return cuts[n];
    }
}