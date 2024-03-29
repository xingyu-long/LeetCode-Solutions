package com.leetcode.matrix;

import java.util.Arrays;

public class _329_LongestIncreasingPathinaMatrix {
    int res = 0;
    // time:O(2^{m+n})

    /**
     * 最差的情况
     * 1 2 3 . . . n
     * 2 3 . . .   n+1
     * 3 . . .     n+2
     * .           .
     * .           .
     * .           .
     * m m+1 . . . n+m-1
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, i, j, 1, null, visited);
            }
        }
        return res;
    }

    public void dfs(int[][] matrix, int row, int col, int count, Integer prev, boolean[][] visited) {
        if (row < 0 || row >= matrix.length ||
                col < 0 || col >= matrix[0].length || visited[row][col] || (prev != null && matrix[row][col] <= prev)) {
            res = Math.max(res, count);
            return;
        }
        if (prev != null && matrix[row][col] > prev) count++;
        prev = matrix[row][col];
        visited[row][col] = true;
        dfs(matrix, row + 1, col, count, prev, visited);
        dfs(matrix, row - 1, col, count, prev, visited);
        dfs(matrix, row, col + 1, count, prev, visited);
        dfs(matrix, row, col - 1, count, prev, visited);
        visited[row][col] = false;
    }

    // dfs + memo
    // time:O(m * n) space:O(m * n) 因为有memo的原因。
    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // 应该全局只需要依赖一个visited和memo数组
        boolean[][] visited = new boolean[m][n];
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int max = dfs(matrix, i, j, null, visited, memo);
                res = Math.max(res, max);
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int row, int col, Integer prev, boolean[][] visited, int[][] memo) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length
                || visited[row][col] || (prev != null && matrix[row][col] <= prev)) {
            return 0;
        }
        if (memo[row][col] != -1) return memo[row][col];

        prev = matrix[row][col];
        visited[row][col] = true;
        // 遇到不符合的就会退出，所以这里负责count++就好
        int down = dfs(matrix, row + 1, col, prev, visited, memo);
        int up = dfs(matrix, row - 1, col, prev, visited, memo);
        int right = dfs(matrix, row, col + 1, prev, visited, memo);
        int left = dfs(matrix, row, col - 1, prev, visited, memo);
        int max = 1 + Math.max(down, Math.max(up, Math.max(left, right)));
        visited[row][col] = false;
        memo[row][col] = max;
        return memo[row][col];
    }
}
