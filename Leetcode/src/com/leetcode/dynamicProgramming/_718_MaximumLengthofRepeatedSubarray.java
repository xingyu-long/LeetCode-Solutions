package com.leetcode.dynamicProgramming;

/**
 * @Date: 04/10/2020, 09/10/2020
 * @Description: DP
 **/
public class _718_MaximumLengthofRepeatedSubarray {

  public int findLength(int[] A, int[] B) {
    // Strict contiguous' 以i，j分别结尾最长的重复序列
    // 这个其实就算暴力解了。
    int m = A.length;
    int n = B.length;
    int[][] dp = new int[m + 1][n + 1];
    int res = 0;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (A[i - 1] == B[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          res = Math.max(res, dp[i][j]);
        }
      }
    }
    return res;
  }
}
