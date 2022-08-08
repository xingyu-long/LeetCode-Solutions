package com.leetcode.matrix;

public class _59_SpiralMatrixII {

    /**
     * 59. Spiral Matrix II
     * When: 2019/05/29
     * Review1:2019/8/5
     * solution:
     * 跟之前那道题一样的思路，只是做了一个值来循环
     * @param n
     * @return
     */
    //time: O(m * n) space: O(m * n)
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int start = 1;
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //向右
            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = start;
                start++;
            }
            rowBegin++;
            //向下
            for (int i = rowBegin; i <= rowEnd; i++) {
                res[i][colEnd] = start;
                start++;
            }
            colEnd--;

            //向左
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    res[rowEnd][i] = start;
                    start++;
                }
            }
            rowEnd--;

            //向上
            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res[i][colBegin] = start;
                    start++;
                }
            }
            colBegin++;
        }
        return res;
    }
}
