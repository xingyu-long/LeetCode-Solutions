package com.leetcode.dynamicProgramming;

import java.util.List;

public class _1301_NumberofPathswithMaxScore {
    /**
     * When: 02/26/2020, 03/10/2020
     * @param board
     * @return
     */
    // 依靠同时两个dp操作。ways这个dp看从哪条最大值的边过来（可能三条边）。dp这个也是看最大的来自哪边。
    // time:O(n^2) space:O(n^2)
    // 这个和unique paths 可以比较
    public int[] pathsWithMaxScore(List<String> board) {
        int mod = (int) Math.pow(10, 9) + 7;
        int n = board.size();
        int[][] dp = new int[n + 1][n + 1];
        int[][] ways = new int[n + 1][n + 1];
        char[][] newBoard = generateBoard(board);
        newBoard[n - 1][n - 1] = newBoard[0][0] = '0';
        ways[n - 1][n - 1] = 1;// 利用外围的一圈达到初始化的目的。这样就可以计算其ways。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (newBoard[i][j] != 'X') {
                    int max = Math.max(Math.max(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]);
                    dp[i][j] = (newBoard[i][j] - '0') + max;
                    if (dp[i + 1][j] == max) {
                        ways[i][j] = (ways[i][j] + ways[i + 1][j]) % mod;
                    }
                    if (dp[i + 1][j + 1] == max) {
                        ways[i][j] = (ways[i][j] + ways[i + 1][j + 1]) % mod;
                    }
                    if (dp[i][j + 1] == max) {
                        ways[i][j] = (ways[i][j] + ways[i][j + 1]) % mod;
                    }
                }
            }
        }
        if (ways[0][0] == 0) return new int[]{0,0};
        return new int[]{dp[0][0], ways[0][0]};
    }


    public char[][] generateBoard(List<String> board) {
        int n = board.size();
        char[][] newBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = board.get(i).charAt(j);
            }
        }
        return newBoard;
    }
}
