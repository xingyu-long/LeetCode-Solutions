package com.leetcode.dynamic_programming;

import java.util.HashMap;

public class _935_KnightDialer {

    public int MOD;
    int[][][] memo;
    // time:O(10N) space:O(N * 4 * 3)
    public int knightDialer2(int N) {
        int res  = 0;
        memo = new int[N + 1][4][3];
        MOD = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                res = (dfs(N, i, j, 1) + res) % MOD;
            }
        }
        return res;
    }
    
    public int dfs(int N, int i, int j, int count) {
        if (i < 0 || i >= 4 || j < 0 || j >= 3 || (i == 3 && j != 1)) return 0;
        if (count == N) return 1;
        if (memo[count][i][j] > 0) return memo[count][i][j];
        int[][] dirs = {{2, 1}, {1, 2}, {2, -1}, {1, -2},
                        {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
        int res = 0;
        for (int[] dir : dirs) {
            res = (res + dfs(N, i + dir[0], j + dir[1], count + 1)) % MOD; 
        }
        memo[count][i][j] = res;
        return res;
    }
}