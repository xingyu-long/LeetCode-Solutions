package com.leetcode.bfs_and_dfs;

/**
 * @Date: 08/01/2020
 * @Description: DFS
 **/
public class _529_Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return new char[][]{};
        }
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        dfs(board, x, y);
        return board;
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] != 'E') return;
        int count = countMines(board, i, j);
        int[] dir = {-1, 0, 1};
        if (count == 0) {
            // search empty block.
            board[i][j] = 'B';
            for (int n1 : dir) {
                for (int n2 : dir) {
                    if (n1 == 0 && n2 == 0) continue;
                    int x = i + n1, y = j + n2;
                    dfs(board, x, y);
                }
            }
        } else {
            board[i][j] = (char)('0' + count);
        }
    }


    private int countMines(char[][] board, int i, int j) {
        int[] dir = {-1, 0, 1};
        int count = 0;
        for (int n1 : dir) {
            for (int n2 : dir) {
                if (n1 == 0 && n2 == 0) continue;
                int x = i + n1, y = j + n2;
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                    continue;
                }
                if (board[x][y] == 'M') count++;
            }
        }
        return count;
    }
}
