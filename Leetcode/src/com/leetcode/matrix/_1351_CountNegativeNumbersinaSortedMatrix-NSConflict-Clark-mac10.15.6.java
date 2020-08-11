package com.leetcode.matrix;

/**
 * @Date: 07/08/2020
 * @Description: Traversal.
 **/
public class _1351_CountNegativeNumbersinaSortedMatrix {

    // 利用和search matrix II一样的方法 达到O(m+n)
    // 针对于行，列都有顺序的情况。从右上角出发
    public int countNegatives(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int row = 0; // 从右上角开始
        int col = n - 1;
        int res = 0;
        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                res += m - row;
                col--;
            } else {
                row++;
            }
        }
        return res;
    }
}
