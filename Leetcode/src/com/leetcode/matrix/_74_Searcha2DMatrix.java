package com.leetcode.matrix;

public class _74_Searcha2DMatrix {

    /**
     * 74. Search a 2D Matrix
     * When: 2019/05/29
     *
     * solution: 利用二分法
     * @param matrix
     * @param target
     * @return
     */
    //time: O(log(m * n)) space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        // 就是一个binary search的题目 主要是二维坐标映射
        int row = matrix.length;
        int col = matrix[0].length;
        int begin = 0;
        int end = row * col - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            int val = matrix[mid / col][mid % col];
            if (val == target) {
                return true;
            } else if (val > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return false;
    }
}
