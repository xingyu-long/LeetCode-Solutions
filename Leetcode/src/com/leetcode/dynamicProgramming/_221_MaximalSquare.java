package com.leetcode.dynamicProgramming;

public class _221_MaximalSquare {
    /**
     *  221. Maximal Square
     *  When: 2019/8/2
     *  Difficulty: Medium

     DP[i][j] 表示到达i, j这个位置最大的正方形长度
     * @param matrix
     * @return
     */
    // 画那个求prefix sum的图表示。。。
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-221-maximal-square/
    // time:O(m * n) space:O(m*n)
    // dp[i][j]表示从0，0到i，j这个位置来说最大的可以形成正方形的边长。
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    // time:O(m * n * k) space:O(m * n)
    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                        - dp[i - 1][j - 1] + matrix[i - 1][j - 1] - '0';
            }
        }
        // print(dp);
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = Math.min(n - j, m - i); k > 0; k--) {
                    int row1 = i;
                    int col1 = j;
                    int row2 = i + k - 1;
                    int col2 = j + k - 1; // 这里要注意k需要减一因为不然就会越界。
                    // System.out.println("row1 = " + row1 + "col1 = " + col1);
                    // System.out.println("row2 = " + row2 + "col2 = " + col2);
                    int sum = dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
                    if (sum == k * k) {
                        res = Math.max(res, sum);
                        break;
                    }
                }
            }
        }
        return res;
    }

    public void print(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}