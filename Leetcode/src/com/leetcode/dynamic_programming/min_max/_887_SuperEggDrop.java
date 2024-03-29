package com.leetcode.dynamic_programming.min_max;

import java.util.Arrays;

/**
 * @Date: 04/11/2020
 * @Description: DP, MinMax
 **/
public class _887_SuperEggDrop {

  // time:O(KNN) TLE
  public int superEggDrop(int K, int N) {
    int[][] memo = new int[K + 1][N + 1];
    for (int[] arr : memo) {
      Arrays.fill(arr, -1);
    }
    return dfs(K, N, memo);
  }

  public int dfs(int k, int n, int[][] memo) {
    // base case 如何定义？？？
    if (k == 1) {
      return n; // 最多可以一步步试
    }
    if (n <= 1) { // N等于0的时候，无法drop即N本身
      return n;
    }
    if (memo[k][n] != -1) {
      return memo[k][n];
    }
    int res = Integer.MAX_VALUE / 2;
    for (int i = 1; i <= n; i++) {
      res = Math.min(res, Math.max(dfs(k - 1, i - 1, memo), dfs(k, n - i, memo)) + 1);
    }
    memo[k][n] = res;
    return res;
  }

  // 优化的思路是利用其单调性，然后使用binary search来优化选取的i，后面看
}
