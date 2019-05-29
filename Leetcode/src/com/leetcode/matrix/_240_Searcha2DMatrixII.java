package com.leetcode.matrix;

public class _240_Searcha2DMatrixII {

    /**
     * 240. Search a 2D Matrix II
     * When: 2019/05/29
     *
     * solution:
     * 要不是就是减少 要不就是增加这个值来靠近target！
     * 因为 其数组的特性（每一列有序，每一行也有序）
     * 所有向右是增加，向下也是增加
     *
     * YouTube讲解网址：
     * https://www.youtube.com/watch?v=FOa55B9Ikfg
     * @param matrix
     * @param target
     * @return
     */
    // time : O(m + n) space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1; // 从右边顶点开始

        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
