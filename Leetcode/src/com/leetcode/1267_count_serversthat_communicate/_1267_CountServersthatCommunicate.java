package com.leetcode;

public class _1267_CountServersthatCommunicate {
    // 相当于先记录每行和每列的情况，最后遍历一遍。
    // 需要好好理解其中的原理
    public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int[] rowNums = new int[grid.length];
        int[] colNums = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rowNums[i]++;
                    colNums[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && (rowNums[i] > 1 || colNums[j] > 1)) {
                    // 表示除了自己，当前行或者列还有其他的。
                    res++;
                }
            }
        }
        return res;
    }
}
