package com.leetcode.matrix;

/**
 * @Date: 05/17/2020
 * @Description: traversal, Matrix
 **/
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
}
