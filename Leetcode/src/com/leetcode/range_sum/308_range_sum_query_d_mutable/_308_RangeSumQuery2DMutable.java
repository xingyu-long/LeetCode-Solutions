/*
 * @Date: 07/16/2022 15:27:32
 * @LastEditTime: 07/16/2022 15:27:33
 * @Description: Binary Indexed Tree, Range Sum 
 */

package com.leetcode.range_sum;

public class _308_RangeSumQuery2DMutable {
    int[][] mat;
    int[][] bit;
    int m;
    int n;

    public _308_RangeSumQuery2DMutable(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        mat = new int[m + 1][n + 1];
        bit = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0)
            return;
        int diff = val - mat[row + 1][col + 1];
        for (int i = row + 1; i < m + 1; i += i & (-i)) {
            for (int j = col + 1; j < n + 1; j += j & (-j)) {
                bit[i][j] += diff;
            }
        }
        mat[row + 1][col + 1] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0)
            return 0;
        return getSum(row2 + 1, col2 + 1) - getSum(row1, col2 + 1) - getSum(row2 + 1, col1) + getSum(row1, col1);
    }

    public int getSum(int row, int col) {
        int res = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                res += bit[i][j];
            }
        }
        return res;
    }
}
