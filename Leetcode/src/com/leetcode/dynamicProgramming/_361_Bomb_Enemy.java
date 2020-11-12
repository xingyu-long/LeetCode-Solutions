package com.leetcode.dynamicProgramming;

/**
 * @Date: 10/13/2020
 * @Description: DP
 **/
public class _361_Bomb_Enemy {
    // 利用四个DP数组即可，表示当目前为止的情况
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] leftToRight = new int[m][n];
        int[][] rightToLeft = new int[m][n];
        int[][] upToDown = new int[m][n];
        int[][] downToUp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = (j == 0 || grid[i][j] == 'W') ? 0 : leftToRight[i][j - 1];
                leftToRight[i][j] = grid[i][j] == 'E' ? t + 1 : t;
            }

            for (int j = n - 1; j >= 0; j--) {
                int t = (j == n - 1 || grid[i][j] == 'W') ? 0 : rightToLeft[i][j + 1];
                rightToLeft[i][j] = grid[i][j] == 'E' ? t + 1 : t;
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int t = (i == 0 || grid[i][j] == 'W') ? 0 : upToDown[i - 1][j];
                upToDown[i][j] = grid[i][j] == 'E' ? t + 1 : t;
            }

            for (int i = m - 1; i >= 0; i--) {
                int t = (i == m - 1 || grid[i][j] == 'W') ? 0 : downToUp[i + 1][j];
                downToUp[i][j] = grid[i][j] == 'E' ? t + 1 : t;
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, leftToRight[i][j] + rightToLeft[i][j] + upToDown[i][j] + downToUp[i][j]);
                }
            }
        }
        return res;
    }
}
