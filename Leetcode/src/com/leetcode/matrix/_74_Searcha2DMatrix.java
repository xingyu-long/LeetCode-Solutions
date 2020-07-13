package com.leetcode.matrix;

/**
 * @Date: 2019/05/29 review1:2019/8/9, 07/08/2020
 * @Description: Binary Search, convert to row and col.
 **/
public class _74_Searcha2DMatrix {

    //time: O(log(m * n)) space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int i = mid / n;
            int j = mid % n;
            if (matrix[i][j] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (matrix[left / n][left % n] == target) {
            return true;
        }
        if (matrix[right / n][right % n] == target) {
            return true;
        }
        return false;
    }
}