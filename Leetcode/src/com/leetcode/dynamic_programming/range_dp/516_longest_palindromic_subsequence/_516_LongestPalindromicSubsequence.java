package com.leetcode.dynamic_programming.range_dp;

import java.util.Arrays;

/**
 * @Date: 04/11/2020
 * @Description: range DP, Palindromic
 **/
public class _516_LongestPalindromicSubsequence {

  // 区间DP，与1312类似
  public int longestPalindromeSubseq(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int n = s.length();
    int[][] memo = new int[n + 1][n + 1];
    for (int[] arr : memo) {
      Arrays.fill(arr, -1);
    }
    return dfs(s, 0, n - 1, memo);
  }

  public int dfs(String s, int i, int j, int[][] memo) {
    if (i == j) {
      return 1;
    }
    if (i > j) {
      return 0;
    }
    if (memo[i][j] != -1) {
      return memo[i][j];
    }
    int res = 0;
    if (s.charAt(i) == s.charAt(j)) {
      res = dfs(s, i + 1, j - 1, memo) + 2;
    } else {
      res = Math.max(dfs(s, i + 1, j, memo), dfs(s, i, j - 1, memo));
      // 类似于哪个最小插入数一样，分别移动一位看是否为palindromic
    }
    memo[i][j] = res;
    return res;
  }
}
