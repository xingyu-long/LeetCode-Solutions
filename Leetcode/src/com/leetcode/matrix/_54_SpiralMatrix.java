package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class _54_SpiralMatrix {

    /**
     * 54. Spiral Matrix
     * When: 2019/05/29
     * Review1: 2019/8/4
     * review2: 11/7/2019
     * solution：实现题，然后使用while 循环，上下左右走
     *
     * @param matrix
     * @return
     */
    // time: O(m * n) space: O(m * n)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        //边界条件
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        //因为每次涉及到更换坐标 for循环无法实现
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // 横着向右走
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            //向下走
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;

            // 向左走
            if (rowBegin <= rowEnd) { // 表示移动col的时候也的看row是否还有
                for (int i = colEnd; i >= colBegin; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            //向上走
            if (colBegin <= colEnd) { // 上面同理
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }
        return res;
    }
}