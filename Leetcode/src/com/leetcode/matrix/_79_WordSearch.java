package com.leetcode.matrix;

public class _79_WordSearch {

    public boolean exist(char[][] board, String word) {
        // go to 4 directions
        // use dfs to search -> index == word.length -> found. 提前结束的backtracking类型。
        // 返回值boolean
        // visited needs to maintain and backtrack.
        // time:O(m * n * len(word)) -> len(word)最差可以是M*N 类似于蛇形走位
        // space:O(m * n)
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (visited[i][j])
            return false;
        if (board[i][j] != word.charAt(index))
            return false;
        visited[i][j] = true;
        boolean left = dfs(board, i, j - 1, word, index + 1, visited);
        boolean right = dfs(board, i, j + 1, word, index + 1, visited);
        boolean up = dfs(board, i - 1, j, word, index + 1, visited);
        boolean down = dfs(board, i + 1, j, word, index + 1, visited);
        boolean res = left || right || up || down;
        visited[i][j] = false;
        return res;
    }
}
