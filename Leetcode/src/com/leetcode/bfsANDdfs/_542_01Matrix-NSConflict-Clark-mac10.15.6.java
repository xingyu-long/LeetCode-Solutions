package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 08/04/2020
 * @Description: BFS
 **/
public class _542_01Matrix {

    // 一旦mark就说明已经是最近的情况了。
    public int[][] updateMatrix(int[][] matrix) {
        // we can use 0 to BFS
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[][]{};
        }
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0], y = curr[1] + dir[1];
                    int step = matrix[curr[0]][curr[1]];
                    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (matrix[x][y] != 0) {
                        matrix[x][y] = step + 1;
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return matrix;
    }
}
