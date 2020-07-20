package com.leetcode.matrix;

/**
 * @Date: 07/17/2020
 * @Description: backtracking
 **/
public class _79_WordSearch {

    /**
     * 79. Word Search When: 2019/05/30 review: 02/06/2020
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        // go to 4 directions
        // use dfs to search -> index == word.length -> found.  提前结束的backtracking类型。 返回值boolean
        // visited needs to maintain and backtrack.
        // time:O(m * n * len(word)) -> len(word)最差可以是M*N 类似于蛇形走位
        // space:O(m * n)
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, 0, word, visited, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 验证顺序有问题，应该把验证放到最前面
    public boolean dfs(char[][] board, int index, String word, boolean[][] visited, int i, int j) {
        // 放在前面，如果最后字符在一行或者一列的边界这样也不会受影响。
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (word.charAt(index) != board[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean up = dfs(board, index + 1, word, visited, i - 1, j);
        boolean down = dfs(board, index + 1, word, visited, i + 1, j);
        boolean right = dfs(board, index + 1, word, visited, i, j + 1);
        boolean left = dfs(board, index + 1, word, visited, i, j - 1);
        if (left || right || down || up) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}
