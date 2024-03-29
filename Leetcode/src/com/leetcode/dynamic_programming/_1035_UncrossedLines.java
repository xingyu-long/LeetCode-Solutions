package com.leetcode.dynamic_programming;

/**
 * @Date: 05/25/2020
 * @Description: DP, subsequence
 **/
public class _1035_UncrossedLines {
    // 分析错了，写成了匹配的移动问题
    // 和这个相同 Longest Common Subsequence
    //time:O(len(A) * len(B)) space:O(len(A) * len(B))
    public int maxUncrossedLines(int[] A, int[] B) {
        /*
             '' 1 4 2
          '' 0  0 0 0 
          1  0  1 1 1
          2  0  1 1 2
          4  0  1 2 2
        */
        if (A == null || B == null) return 0;
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) { // 表示在i和j这个位置之前的构成最大 + 当前的一组
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 查看之前能够构成的最大
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                // System.out.print(dp[i][j] + " ");
            }
            // System.out.println();
        }
        return dp[m][n];
    }
}