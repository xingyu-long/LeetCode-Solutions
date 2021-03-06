package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _1380_LuckyNumbersinaMatrix {
    /**
     * When: 03/15/2020
     * @param matrix
     * @return
     */
    // time:O(mn) space:O(m)
    // set intersection
    public List<Integer> luckyNumbers (int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new ArrayList<>();
        HashSet<Integer> minOfRows = new HashSet<>();
        for (int[] row : matrix) {
            int min = row[0];
            for (int n : row) {
                min = Math.min(min, n);
            }
            minOfRows.add(min);
        } 
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            int max = matrix[0][j];
            for (int i = 0; i < m; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            if (minOfRows.contains(max)) res.add(max);
        }
        return res;
    }
}