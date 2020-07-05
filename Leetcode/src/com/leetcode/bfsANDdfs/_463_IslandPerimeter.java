package com.leetcode.bfsANDdfs;

/**
 * @Date: 06/06/2020
 * @Description: traversal
 **/
public class _463_IslandPerimeter {

    // time:O(m*n)
    public int islandPerimeter(int[][] grid) {
        // 这里只有一个island有效，所以不用搜索，直接遍历就好了
        // 每次看一边的规律 左边能够算入的话只能是左边没有或者当前位于第一个位置。
        int res = 0;
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i == 0 || grid[i - 1][j] == 0) {
                    res++; // check the up
                }
                if (j == 0 || grid[i][j - 1] == 0) {
                    res++; // check the left
                }
                if (i == grid.length - 1 || grid[i + 1][j] == 0) {
                    res++; // check the bottom
                }
                if (j == grid[0].length - 1 || grid[i][j + 1] == 0) {
                    res++; // check the right
                }
            }
        }
        return res;
    }
}
