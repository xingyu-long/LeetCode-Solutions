package com.leetcode.array;

public class _289_GameofLife {

    /**
     *  289. Game of Life
     *  When:2019/8/4
     *  Difficulty: Medium
     * @param board
     */
    // time:O(m * n) space:O(m * n)
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;

        int[] neighbors = {0, -1, 1}; // 用这个并且结合3*3 loop访问八个邻居

        int m = board.length;
        int n = board[0].length;

        int[][] copyBoard =  new int[m][n];
        // copy original data
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int numOfLive = 0;

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        // 不包括原来的这个点
                        if (!(neighbors[a] == 0 && neighbors[b] == 0)) {
                            int r = i + neighbors[a];
                            int c = j + neighbors[b];

                            if ((r < m && r >= 0) && (c < n && c >= 0) && copyBoard[r][c] == 1) {
                                numOfLive++;
                            }
                        }
                    }
                }
                // rule 1 or 3
                if ((copyBoard[i][j] == 1) && (numOfLive < 2 || numOfLive > 3)) {
                    board[i][j] = 0;
                }
                if (numOfLive == 3 && copyBoard[i][j] == 0) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // time:O(m * n) space:O(1)
    // 降低其空间复杂度代表需要 board能够反应以前的状态（在经过改变之后）
    // 如果当前是活的，满足死的条件 则就是赋值为-1
    // 当前是是的，满足活的条件，则赋值为2
    // 查询周围1的时候用绝对值等于1即可
    public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0) return;

        int[] neighbors = {0, -1, 1}; // 用这个并且结合3*3 loop访问八个邻居

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int numOfLive = 0;

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        // 不包括原来的这个点
                        if (!(neighbors[a] == 0 && neighbors[b] == 0)) {
                            int r = i + neighbors[a];
                            int c = j + neighbors[b];

                            if ((r < m && r >= 0) && (c < n && c >= 0) && Math.abs(board[r][c]) == 1) {
                                numOfLive++;
                            }
                        }
                    }
                }
                // rule 1 or 3
                if ((board[i][j] == 1) && (numOfLive < 2 || numOfLive > 3)) {
                    board[i][j] = -1;
                }
                if (numOfLive == 3 && board[i][j] == 0) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}