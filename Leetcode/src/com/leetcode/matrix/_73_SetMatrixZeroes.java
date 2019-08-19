package com.leetcode.matrix;

public class _73_SetMatrixZeroes {


    /**
     * 73. Set Matrix Zeroes
     * When: 2019/05/29
     * review1:2019/8/5 8/18
     * solution:
     * 利用[0][j], [i][0]来做标记，然后把这两个的行和列分别置为0  最后注意[0][0]这个点 如果也为0 就将第0列和第0行设置为0
     *
     * Test case:
     * [0,1,2]
     * [3,4,5]
     * [6,0,7]
     *
     * (1) col = true, row = true
     * 循环到[2][1]
     * (2) 然后将[0][1] 以及[2][0]置为0
     * [0,0,2]
     * [3,4,5]
     * [0,0,7]
     * (3) 从[1][1]开始循环，然后[2][0]为0 所以将该行设置为0
     * [0,0,2]
     * [3,4,5]
     * [0,0,0]
     * (4) 从[1][1]开始循环，然后[0][1]为0 所以将该列设置为0
     * [0,0,2]
     * [3,0,5]
     * [0,0,0]
     * (5) col 为true 将第0列置为0
     * [0,0,2]
     * [0,0,5]
     * [0,0,0]
     * (6) row 为true 将第0行置为0
     * [0,0,0]
     * [0,0,5]
     * [0,0,0]
     * @param matrix
     */
    // 最简单的方法是通过一个 m * n数组作为标记，然后找寻这里面的0的位置，然后重置原数组的行以及列
    // 第二个是使用两个一维数组，一个保持行是否为0的情况，一个保留列是否为0的情况
    // 就是下面这种space也只是1的情况，相当于发现为0的情况就置当前行的第一个和当前列的第一个为0，然后检查第一列和第一行的情况进行置0
    //time: O(m * n) space: O(1)
    public void setZeroes(int[][] matrix) {
        // 使用[0][j], [i][0]相当于记录是否需要赋值为0
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row = false;
        boolean col = false;
        // 做标记
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if (i == 0) {
                        row = true;
                    }
                    if (j == 0) {
                        col = true;
                    }
                }
            }
        }

        // 横着来
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 竖着来
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
