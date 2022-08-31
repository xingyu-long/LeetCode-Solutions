/*
 * @Date: 01/25/2020 20:55:42
 * @LastEditTime: 01/23/2021 11:17:10
 * @Description: HashMap,
 */
package com.leetcode.matrix;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _1329_SorttheMatrixDiagonally {

    // Use PriorityQueue instead of sorting each time.
    public int[][] diagonalSort2(int[][] mat) {
        if (mat == null || mat.length == 0 ||
                mat[0] == null || mat[0].length == 0)
            return new int[][] {};
        int m = mat.length;
        int n = mat[0].length;
        // use i - j to identify each diag.
        Map<Integer, PriorityQueue<Integer>> diag = new HashMap<>();
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
