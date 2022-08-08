package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 07/16/2020
 * @Description: BFS
 **/
public class _542_0_1_Matrix {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    res[i][j] = find(matrix, i, j);
                }
            }
        }
        return res;
    }

    private int find(int[][] matrix, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int dist = 0;
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if  (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                        continue;
                    }
                    if (matrix[x][y] == 0) {
                        return dist + 1;
                    } else {
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}
