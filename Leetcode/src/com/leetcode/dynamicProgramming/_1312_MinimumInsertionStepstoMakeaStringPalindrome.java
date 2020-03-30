/*
 * @Date: 2020-01-06 11:09:42
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-29 23:11:19
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1312_MinimumInsertionStepstoMakeaStringPalindrome {
    // 就先了解到Top-Down形式就可以了
    // recursion + memo
    // time:O(n^2) space:O(n^2)
    // 这里不用设置为Integer.max 因为每次都得到结果，没必要比较
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) return 0;
        int[][] memo = new int[s.length()][s.length()];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return helper(s, 0, s.length() - 1, memo);
    }

    public int helper(String s, int start, int end, int[][] memo) {
        if (memo[start][end] != -1) return memo[start][end];
        if (start >= end) return 0;
        int res = 0;
        if (s.charAt(start) == s.charAt(end)) {
            res = helper(s, start + 1, end - 1, memo);
        } else {
            res = Math.min(helper(s, start, end - 1, memo), helper(s, start + 1, end, memo)) + 1;
        }
        memo[start][end] = res;
        return memo[start][end];
    }

    // Bottom Up: 仔细观察转移方程并且画图发现 是一小块一小块构造。分别代表了不同长度的时候的构造
    // 1 2 3
    // - - -
    // --|--
    // -----
    public int minInsertions2(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
