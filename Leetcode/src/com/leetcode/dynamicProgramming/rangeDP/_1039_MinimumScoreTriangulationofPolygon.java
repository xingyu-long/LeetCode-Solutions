package com.leetcode.dynamicProgramming.rangeDP;

import java.util.Arrays;

/**
 * @Date: 04/22/2020
 * @Description: 区间DP
 **/
public class _1039_MinimumScoreTriangulationofPolygon {
    // time:(N^3) space:O(n^2)
    public int minScoreTriangulation(int[] A) {
        // 分区间DP
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] memo = new int[n + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(A, 0, n - 1, memo);
    }

    public int dfs(int[] A, int i, int j, int[][] memo) {
        if ((j - i + 1) < 3) {
            return 0; // 中间的顶点数不够就无法拆分
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            res = Math.min(res, dfs(A, i, k, memo) + A[i] * A[j] * A[k] + dfs(A, k, j, memo));
        }
        memo[i][j] = res;
        return res;
    }
}
