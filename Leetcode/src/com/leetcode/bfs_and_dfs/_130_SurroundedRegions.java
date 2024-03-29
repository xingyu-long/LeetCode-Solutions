package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _130_SurroundedRegions {

    /**
     *  130. Surrounded Regions
     *  When: 2019/05/31
     *  Review1:2019/7/25
     *
     * solution:
     * 反着来 从O出发 看周围是否存在相同的，如果有的话，
     * 将其置为1，然后循环，如果有1 最后就恢复O 其他是O的 就恢复成X
     * @param board
     */
    // 本质不用visited数组，跟island一样
    // 无法从border进去链接的O们，肯定会直接变成X，所以只需要查找与border连接的O将其转为'1'转存，然后循环，里面这些1就成O。里面的O就是X
    // time:O(n * m) space:O(n * m) ？？ 个调用 for stack
    public void solve(char[][] board) {

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


    public void solve2(char[][] board) {
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) return;
        for (int i = 0; i < board.length; i++) {
            bfs(board, i, 0);
            bfs(board, i, board[0].length - 1);
        }

        for (int i = 0; i < board[0].length; i++) {
            bfs(board, 0, i);
            bfs(board, board.length - 1, i);
        }

        // 遍历每一个元素，将1反转为O，O为X
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // BFS
    public void bfs(char[][] board, int row, int col) {
        if (board[row][col] == 'O') {
            board[row][col] = '1';
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{row, col});
            int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                for (int[] dir : dirs) {
                    int x = point[0];
                    int y = point[1];
                    if (isValid(board, x + dir[0], y + dir[1])) {
                        x += dir[0];
                        y += dir[1];
                        board[x][y] = '1';
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
    }

    public boolean isValid(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') return false;
        return true;
    }

    // 如果遇到相反的情况，0包围1
    // 0 0 1 0 0     0 0 1 0 0
    // 0 1 0 1 0 ->  0 1 1 1 0
    // 0 1 0 1 0     0 1 1 1 0
    // 0 0 1 0 0     0 0 1 0 0
    // 一样的思路，从四边出发遇到0以及相连的就变成2之后将其他没有受关联的0转为1，然后再讲2转为0
}
