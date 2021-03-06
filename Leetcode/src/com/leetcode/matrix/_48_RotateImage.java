package com.leetcode.matrix;

public class _48_RotateImage {

    /**
     * 48. Rotate Image
     * When: 2019/05/28
     * Review1: 2019/8/4
     * <p>
     * You are given an n x n 2D matrix representing an image.
     * <p>
     * Rotate the image by 90 degrees (clockwise).
     * <p>
     * eg:
     * Given input matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * rotate the input matrix in-place such that it becomes:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * <p>
     * solution:
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * <p>
     * 先按照1,5,9 斜边对折
     * [1,4,7]
     * [2,5,8]
     * [3,6,9]
     * <p>
     * 然后垂直对折
     * [7,4,1]
     * [8,5,2]
     * [9,6,3]
     *
     * @param matrix
     */
    // time: O(m * n) space:O(1)
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            // 这里设置为j=i 是因为否则运行到后面 就会反转回来
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            // 因为这里是对半分，然后使用长度的差值来计算 对称的点
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    // 关于rotate的题目。
    public void rotateByLeftDiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            // 这里设置为j=i 是因为否则运行到后面 就会反转回来
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void rotateByRightDiagonal(int[][] matrix) {
        /**
         {1,2,3}          {9,6,3}
         {4,5,6}  ->  res {8,5,2}
         {7,8,9}          {7,4,1}

         先对折
         {3,2,1}
         {6,5,4}
         {9,8,7}
         然后 leftFlip
         {3,6,9}
         {2,5,8}
         {1,4,7}
         之后对折即可
         * */
    }

    public void FlipByVertical(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            // 因为这里是对半分，然后使用长度的差值来计算 对称的点
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }
}
