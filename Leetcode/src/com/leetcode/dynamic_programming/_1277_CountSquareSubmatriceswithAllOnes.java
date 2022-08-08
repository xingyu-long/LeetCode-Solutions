package com.leetcode.dynamic_programming;

/**
 * @Date: 05/21/2020
 * @Description: range sum, DP
 **/
public class _1277_CountSquareSubmatriceswithAllOnes {
    public int countSquares(int[][] matrix) {
        // 用累计和来暴力求解？？？？ 就是求减 然后看是否等于里面的个数（则表明是square，然后去尝试各种边长）
        // time: N^3 space:O(m * n)
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
            }
        }
        // print(dp);
        int maxEdge = Math.min(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // System.out.println("from row1 = " + i + " col1 = " + j);
                for (int k = 0; k < maxEdge; k++) {
                    if (i + k >= m || j + k >= n) break;
                    int row = i + k;
                    int col = j + k;
                    int sum = dp[row + 1][col + 1] - dp[i][col + 1] - dp[row + 1][j] + dp[i][j];
                    // System.out.println("to row2 = " + row + " col2 = " +  col + " sum = " + sum + " edge = " + (k + 1));
                    if (sum == (k + 1) * (k + 1)) {
                        // System.out.println("i = " + i + " j = " + j + " k = " + (k + 1));
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int countSquares2(int[][] matrix) {
        // 依然计算其边长，然后边长为多大表明可以构建成几个不同的正方形
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j])) + 1;
                    res += dp[i + 1][j + 1];
                }
            }
        }
        return res;
    }
}
