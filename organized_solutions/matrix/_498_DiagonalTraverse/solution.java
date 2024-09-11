/*
 * @Date: 05/17/2020 11:16:20
 * @LastEditTime: 12/25/2020 10:35:08
 * @Description: traversal, Matrix
 */
package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _498_DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = 0, dir = 0;
        int[] res = new int[m * n];
        int[][] dirs = {{-1, 1}, {1, -1}}; // 右上，左下
        for (int i = 0; i < m * n; i++) {
            res[i] = matrix[r][c];
            r += dirs[dir][0];
            c += dirs[dir][1];

            if (r >= m) {
                r = m - 1;
                c += 2;
                dir = 1 - dir;
            }
            if (c >= n) {
                c = n - 1;
                r += 2;
                dir = 1 - dir;
            }
            if (r < 0) {
                r = 0;
                dir = 1 - dir;
            }
            if (c < 0) {
                c = 0;
                dir = 1 - dir;
            }
        }
        return res;
    }

    // 利用和的规律
    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i + j;
                map.putIfAbsent(index, new ArrayList<>());
                map.get(index).add(matrix[i][j]);
            }
        }
        int[] res = new int[m * n];
        int k = 0;
        boolean flag = false;
        for (int i = 0; i <= m + n - 2; i++) {            
            List<Integer> list = map.get(i);
            if (!flag) {
                Collections.reverse(list);
            } 
            for (int j = 0; j < list.size(); j++) {
                res[k++] = list.get(j);
            }
            flag = !flag;
        }
        return res;
    }
}
