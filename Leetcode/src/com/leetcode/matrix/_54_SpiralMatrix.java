package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/05/29, 2019/8/4, 11/7/2019, 05/24/2020
 * @Description: Simulate
 **/
public class _54_SpiralMatrix {

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
            if (++rowBegin > rowEnd) {
                break;
            }
            //向下走
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            if (--colEnd < colBegin) {
                break;
            }

            // 向左走
            for (int i = colEnd; i >= colBegin; i--) {
                res.add(matrix[rowEnd][i]);
            }

            if (--rowEnd < rowBegin) {
                break;
            }
            //向上走
            for (int i = rowEnd; i >= rowBegin; i--) {
                res.add(matrix[i][colBegin]);
            }
            if (++colBegin > colEnd) {
                break;
            }
        }
        return res;
    }
}