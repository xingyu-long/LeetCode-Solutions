package com.leetcode.rangeSum;

public class _304_RangeSumQuery2DImmutable {

    /**
     *  304. Range Sum Query 2D - Immutable
     *  When: 2019/7/7

        solution1:
            一行一行的进行缓存，原理和303一致
        solution2:
            使用面积来cache并且计算面积的时候也有技巧（注意那个-dp[r][c]，这里是为了防止重复计算）
     *
     */
    /** Caching rows*/
    private int[][] dp;

    public _304_RangeSumQuery2DImmutable(){

    }
    //time: O(n^2) space: O(n^2)
    public _304_RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[i][j + 1] = dp[i][j] + matrix[i][j];
            }
        }
    }

    //time:O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += dp[i][col2 + 1] - dp[i][col1];
        }
        return sum;
    }

    // https://www.youtube.com/watch?v=MSNSqU3BnXk
    /** Smarter Caching*/
    // there should be construct func (not use void in actual problem)
    //time: O(n^2) space: O(n^2)
    public void NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
            }
        }
        print(dp);
    }

    public void print(int[][] dp) {
        int m = dp.length;
        int n = dp[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    //time:O(1)
    public int sumRegion2(int row1, int col1, int row2, int col2) {
        // 这里的dp[row1][col2 + 1]是因为本身就不包含row1这一行，所以相当于已经+1了
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
