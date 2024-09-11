package com.leetcode.matrix;

import java.util.Arrays;

/**
 * @Date: 07/15/2020
 * @Description: Matrix
 **/
public class _1504_CountSubmatricesWithAllOnes {
    // 利用brute force来理解
    // 以当前i,j作为构成matrix的左上角顶点
    // time:O(m^2 n^2)
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += helper(mat, i, j);
            }
        }
        return res;
    }
    // 当走到0的时候说明我们不再会用到。因为i,j为左上角构成的matrix不能用0.
    private int helper(int[][] mat, int x, int y) {
        int m = mat.length, n = mat[0].length;
        int count = 0, bound = n;
        for (int i = x; i < m; i++) {
            for (int j = y; j < bound; j++) {
                if (mat[i][j] == 1) count++;
                else bound = j;
            }
        }
        return count;
    }

    // 相当于把当前行的所有点看成顶点的情况
    public int numSubmat2(int[][] mat) {
        if (mat == null || mat.length == 0 ||
            mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int m = mat.length, n = mat[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            int[] h = new int[n];
            Arrays.fill(h, 1);
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    h[k] &= mat[j][k];
                }
                int count = countOneRow(h);
                res += count;
            }
        }
        return res;
    }

    private int countOneRow(int[] h) {
        int res = 0, length = 0;
        for (int i = 0; i < h.length; i++) {
            length = (h[i] == 0 ? 0 : length + 1);
            res += length;
        }
        return res;
    }
    // 第二种解法更加类似于maximum rectangle.

    public static void main(String[] args) {
        _1504_CountSubmatricesWithAllOnes countMat = new _1504_CountSubmatricesWithAllOnes();
        int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        countMat.numSubmat(mat);
    }
}
