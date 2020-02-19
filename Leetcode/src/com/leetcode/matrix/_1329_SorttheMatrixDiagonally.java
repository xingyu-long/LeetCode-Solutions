package com.leetcode.matrix;

import java.util.*;

public class _1329_SorttheMatrixDiagonally {
    // date: 01/25/2020
    // time: O(m + n) max log max(# of diag)
    // space: O(m * n) clone
    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) return new int[][]{};
        int m = mat.length;
        int n = mat[0].length;
        for (int j = n - 1; j >= 0; j--) {
            int col = j;
            int row = 0;
            List<Integer> list = new LinkedList<>();
            while (row < m && col < n) {
                list.add(mat[row][col]);
                row++;
                col++;
            }
            Collections.sort(list);
            col = j;
            row = 0;
            int k = 0;
            while (row < m && col < n) {
                mat[row][col] = list.get(k++);
                row++;
                col++;
            }
        }

        for (int i = 1; i < m; i++) {
            int col = 0;
            int row = i;
            List<Integer> list = new LinkedList<>();
            while (row < m && col < n) {
                list.add(mat[row][col]);
                row++;
                col++;
            }
            Collections.sort(list);
            col = 0;
            row = i;
            int k = 0;
            while (row < m && col < n) {
                mat[row][col] = list.get(k++);
                row++;
                col++;
            }
        }
        return mat;
    }

    //
    public int[][] diagonalSort2(int[][] mat) {
        if (mat == null || mat.length == 0 ||
                mat[0] == null || mat[0].length == 0)
            return new int[][]{};
        int m = mat.length;
        int n = mat[0].length;
        // use i - j to identify each diag.
        HashMap<Integer, PriorityQueue<Integer>> diag = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diag.putIfAbsent(i - j, new PriorityQueue<>());
                diag.get(i - j).add(mat[i][j]);
            }
        }

        // poll each diag
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = diag.get(i - j).poll();
            }
        }
        return mat;
    }
}
