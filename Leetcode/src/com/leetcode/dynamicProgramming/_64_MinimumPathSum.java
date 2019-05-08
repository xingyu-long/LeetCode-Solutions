package com.leetcode.dynamicProgramming;

public class _64_MinimumPathSum {

    /**
     *  64. Minimum Path Sum
     *      Given a m x n grid filled with non-negative numbers,
     *      find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     *
     *      Note: You can only move either down or right at any point in time.
     *
     *      Example 1:
     *      [[1,3,1],
     *      [1,5,1],
     *      [4,2,1]]
     *      Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
     *
     *  time: 2019/05/08
     *
     * @param grid
     * @return
     */
    // time: O(m * n) space: O(1)(没有新建二维数组)
    public int minPathSum(int[][] grid) {
        //主体思路和之前差不多，就是计算第一行和第一列的，但这里是一个累和的过程
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 越界问题呢？ 因为可能执行grid[0][0]这个点 所以没事
                if (i == 0 && j != 0) grid[i][j] += grid[i][j - 1]; // 第一行
                if (i != 0 && j == 0) grid[i][j] += grid[i-1][j]; // 第一列
                if (i != 0 && j != 0) {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
