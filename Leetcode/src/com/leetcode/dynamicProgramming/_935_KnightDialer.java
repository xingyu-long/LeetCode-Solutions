package com.leetcode.dynamicProgramming;

import java.util.HashMap;

public class _935_KnightDialer {

    public int MOD;
    // TLE 优化！
    HashMap<String, Integer> map;
    /**
     * When: 03/06/2020
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
        int res  = 0;
        map = new HashMap<>();
        MOD = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res = (dfs(board, N, i, j, 1) + res) % MOD;
            }
        }
        res = (dfs(board, N, 3, 1, 1) + res) % MOD;
        return res;
    }
    
    public int dfs(int[][] board, int N, int i, int j, int count) {
        String key = i + "-" + j + "-" + count;
        if (map.get(key) != null) return map.get(key);
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return 0;
        if (board[i][j] == -1) return 0;
        if (count == N) {
            if (board[i][j] != -1) return 1;
            return 0;
        }
        int[][] dirs = {{2, 1}, {1, 2}, {2, -1}, {1, -2},
                        {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
        int res = 0;
        for (int[] dir : dirs) {
            res = (res + dfs(board, N, i + dir[0], j + dir[1], count + 1)) % MOD; 
        }
        map.put(key, res);
        return res;
    }

    int[][][] memo;
    // time:O(10N) space:O(N * 4 * 3)
    public int knightDialer2(int N) {
        int res  = 0;
        memo = new int[N + 1][4][3];
        MOD = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                res = (dfs2(N, i, j, 1) + res) % MOD;
            }
        }
        return res;
    }
    
    public int dfs2(int N, int i, int j, int count) {
        if (i < 0 || i >= 4 || j < 0 || j >= 3 || (i == 3 && j != 1)) return 0;
        if (count == N) return 1;
        if (memo[count][i][j] > 0) return memo[count][i][j];
        int[][] dirs = {{2, 1}, {1, 2}, {2, -1}, {1, -2},
                        {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
        int res = 0;
        for (int[] dir : dirs) {
            res = (res + dfs2(N, i + dir[0], j + dir[1], count + 1)) % MOD; 
        }
        memo[count][i][j] = res;
        return res;
    }
}