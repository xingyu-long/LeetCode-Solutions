package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 07/14/2020
 * @Description: BFS, DFS
 **/
public class _417_PacificAtlanticWaterFlow {
    // 类似于surround region, 778. Swim in Rising Water
    // 指当前点同时能走到Pacific和Atlantic的情况
    // time:(m + n + m * n)
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int m = matrix.length, n = matrix[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // 从四条边开始往里找

        for (int i = 0; i < m; i++) {
            canReach(i, 0, null, matrix, pacific);
            canReach(i, n - 1, null, matrix, atlantic);
        }

        for (int j = 0; j < n; j++) {
            canReach(0, j, null, matrix, pacific);
            canReach(m - 1, j, null, matrix, atlantic);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void canReach(int i, int j, Integer prev, int[][] matrix, boolean[][] visited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) return;
        if (visited[i][j]) return;
        // 因为是从外围开始考虑，所以这里是小于prev就停止
        if (prev != null && matrix[i][j] < prev) return;
        visited[i][j] = true;
        canReach(i, j - 1, matrix[i][j], matrix, visited);
        canReach(i, j + 1, matrix[i][j], matrix, visited);
        canReach(i - 1, j, matrix[i][j], matrix, visited);
        canReach(i + 1, j, matrix[i][j], matrix, visited);
    }
}
