package com.leetcode.dynamicProgramming;

public class _312_BurstBalloons {
    /**
     *  312. Burst Balloons
     *  When:2019/8/1
     *  Difficulty: Hard


        DP[i][j] 代表i~j的情况
        状态转移方程: dp[i][j] = max(dp[i][j], dp[i][x – 1] + nums[i – 1] * nums[x] * nums[j + 1] + dp[x + 1][j]);
        都是扎最后一个气球
        https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
        https://www.cnblogs.com/grandyang/p/5006441.html
     */
    // time:O(n^3) space:O(n^2)
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        return helper(1, n, arr, dp);
    }

    private int helper(int i, int j, int[] nums, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] > 0) return dp[i][j];
        for (int x = i; x <= j; x++) {
            dp[i][j] = Math.max(dp[i][j], helper(i, x - 1, nums, dp)
                    + nums[i - 1] * nums[x] * nums[j + 1]
                    + helper(x + 1, j, nums, dp));
        }
        return dp[i][j];
    }
}