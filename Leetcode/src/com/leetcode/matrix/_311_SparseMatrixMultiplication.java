package com.leetcode.matrix;

public class _311_SparseMatrixMultiplication {
    // 就是矩阵相乘
    // m * n multiple n * o -> m * n;
    // time: O(m * o * n)
    public int[][] multiply(int[][] A, int[][] B) {
        if (A[0].length != B.length) return new int[][]{};
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                // multi
                int sum = 0;
                for (int k = 0; k < A[0].length; k++) {
                    sum += A[i][k] * B[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}
