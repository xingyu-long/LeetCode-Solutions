package com.leetcode.matrix;

public class _240_Searcha2DMatrixII {

    /**
     * 240. Search a 2D Matrix II
     * When: 2019/05/29
     * review1:2019/8/9
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
        /**
         * 也可以从左下方那个点开始，因为满足 可以减（向上走），可以加（向右走）的特点
         * 只需要修改 row = matrix.length - 1;
         *           col = 0;
         *   while (row >= 0 && col <= matrix[0].length - 1) {
         *          里面分别是 row -- （向上走）
         *          col ++ （向右走）
         *   }
         */
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
