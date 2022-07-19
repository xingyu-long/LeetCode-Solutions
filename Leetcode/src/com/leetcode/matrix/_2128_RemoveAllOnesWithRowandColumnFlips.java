/*
 * @Date: 07/18/2022 10:32:55
 * @LastEditTime: 07/18/2022 10:32:55
 * @Description: You need to fill out
 */
package com.leetcode.matrix;

public class _2128_RemoveAllOnesWithRowandColumnFlips {
    // 转换的次数都不重要，可以选择行或者列翻转。
    // 如果要满足条件，可以拿出第一行作为对照行，只能是完全相同或者是完全相反，否则翻转的时候都会受到影响
    // time: O(m * n)
    public boolean removeOnes(int[][] grid) {
        int m = grid.length;
        for (int i = 1; i < m; i++) {
            if (checkSame(grid, 0, i))
                continue;
            if (checkFlip(grid, 0, i))
                continue;
            return false;
        }
        return true;
    }

    public boolean checkSame(int[][] grid, int row1, int row2) {
        int n = grid[0].length;
        for (int j = 0; j < n; j++) {
            if (grid[row1][j] != grid[row2][j]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkFlip(int[][] grid, int row1, int row2) {
        int n = grid[0].length;
        for (int j = 0; j < n; j++) {
            if (grid[row1][j] != 1 - grid[row2][j]) {
                return false;
            }
        }
        return true;
    }
}
