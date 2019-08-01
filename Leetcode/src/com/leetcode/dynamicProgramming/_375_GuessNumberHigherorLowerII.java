package com.leetcode.dynamicProgramming;

public class _375_GuessNumberHigherorLowerII {
    // 这里说需要保证，那么就需要在猜的左右情况取最差，即最大的情况。然后取出最小
    // https://www.youtube.com/watch?v=VfJPDNG0nYM

    /**
     *  375. Guess Number Higher or Lower II
     *  When:2019/8/1
     *  solution: 最小的最大值情况

        Test case:
        n = 5
        1, 2, 3, 4, 5
        进入for loop
            1 + max(helper(1, 0), helper(2, 5))
            2 + max(helper(1, 1), helper(3, 5))
            3 + max(helper(1, 2), helper(4, 5))
            4 + max(helper(1, 3), helper(5, 5))
            5 + max(helper(1, 4), helper(6, 5))
        然后依次计算得到
        dp[i][j] 指 i~j的情况
     */
    // time: O(n^3) 因为需要i, j, x三个循环 time:O(n^2)
    int[][] dp;

    public int getMoneyAmount(int n) {
        dp = new int[n + 1][n + 1];
        return helper(1, n);
    }

    private int helper(int i, int j) {
        if (i >= j) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            res = Math.min(res, x + Math.max(helper(i, x - 1), helper(x + 1, j)));
        }
        dp[i][j] = res;
        return res;
    }
}
