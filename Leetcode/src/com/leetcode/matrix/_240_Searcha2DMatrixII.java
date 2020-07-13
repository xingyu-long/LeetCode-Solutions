package com.leetcode.matrix;

/**
 * @Date: 2019/05/29 review1:2019/8/9 07/08/2020
 * @Description: Traversal.
 **/
public class _240_Searcha2DMatrixII {

    // time : O(m + n) space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
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
