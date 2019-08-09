package com.leetcode.matrix;

public class _74_Searcha2DMatrix {

    /**
     * 74. Search a 2D Matrix
     * When: 2019/05/29
     * review1:2019/8/9
     * solution: 利用二分法
     *
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

    // 修改成模板样子的binary search
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int val = matrix[mid / n][mid % n];
            if (val >= target) { // 找到第一个 >= target的数字
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return matrix[lo / n][lo % n] == target;
    }
}