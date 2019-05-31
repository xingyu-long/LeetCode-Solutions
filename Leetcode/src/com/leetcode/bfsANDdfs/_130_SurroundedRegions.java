package com.leetcode.bfsANDdfs;

public class _130_SurroundedRegions {

    /**
     * 130. Surrounded Regions
     * When: 2019/05/31
     *
     * solution: 
     * 反着来 从O出发 看周围是否存在相同的，如果有的话，
     * 将其置为1，然后循环，如果有1 最后就恢复O 其他是O的 就恢复成X
     * @param board
     */
    public void solve(char[][] board) {
        // 反着来 从O出发 看周围是否存在相同的，如果有的话，
        // 将其置为1，然后循环，如果有1 最后就恢复O 其他是O的 就恢复成X
        int m = board.length;
        if (m == 0 || board == null) return;
        int n = board[0].length;
        //检查第一行 和 最后一行
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }
        // 检查第一列和最后一列
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i,  int j) {
        if (i < 0 || j < 0 || i >= board.length
                || j >= board[0].length || board[i][j] != 'O') // 这里应该是非O就return 因为后面生成了一些1
            return;
        board[i][j] = '1';
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
    }

}
