package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _576_Out_of_Boundary_Paths {
    private long[][][] memo;
    private int MOD;
    public int findPaths(int m, int n, int N, int i, int j) {
        memo = new long[m + 1][n + 1][N + 1];
        MOD = (int) Math.pow(10, 9) + 7;
        for (long[][] arrs : memo) {
            for (long[] arr : arrs) {
                Arrays.fill(arr, -1);
            }
        }
        return (int) dfs(m, n, N, i, j);
    }

    public long dfs(int m, int n, int N, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (N == 0) return 0;
        if (memo[i][j][N] != -1) return memo[i][j][N];
        long left = dfs(m, n, N - 1, i, j - 1);
        long right = dfs(m, n, N - 1, i, j + 1);
        long up = dfs(m, n, N - 1, i - 1, j);
        long down = dfs(m, n, N - 1, i + 1, j);
        long res = (left + right + up + down) % MOD;
        memo[i][j][N] = res;
        return res;
    }
}
