/*
 * @Date: 08/02/2022 13:53:41
 * @LastEditTime: 08/02/2022 13:55:35
 * @Description: Simulation, Matrix
 */
package com.leetcode.simulation;

public class _723_CandyCrush {
    public int[][] candyCrush(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new int[][] {};
        }

        int m = board.length;
        int n = board[0].length;

        boolean shouldContinue = false;
        // crush horizontally
        // 检查当前的三个元素是否相同
        for (int i = 0; i < m; i++) {
            for (int j = 0; j + 2 < n; j++) {
                int value = Math.abs(board[i][j]);
                if (value > 0 && value == Math.abs(board[i][j + 1]) && value == Math.abs(board[i][j + 2])) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -value;
                    shouldContinue = true;
                }
            }
        }

        // crush vertically
        for (int j = 0; j < n; j++) {
            for (int i = 0; i + 2 < m; i++) {
                int value = Math.abs(board[i][j]);
                if (value > 0 && value == Math.abs(board[i + 1][j]) && value == Math.abs(board[i + 2][j])) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -value;
                    shouldContinue = true;
                }
            }
        }

        // drop vertically (backwards)
        for (int j = 0; j < n; j++) {
            int end = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] >= 0) {
                    board[end--][j] = board[i][j];
                }
            }
            // fill the rest with 0s
            for (int i = end; i >= 0; i--) {
                board[i][j] = 0;
            }
        }
        return shouldContinue ? candyCrush(board) : board;
    }
}
