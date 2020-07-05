package com.leetcode.dynamicProgramming.rangeDP;

/**
 * @Date: 04/11/2020
 * @Description: range DP, Palindromic
 **/
public class _312_BurstBalloons {

    // https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
    // https://www.cnblogs.com/grandyang/p/5006441.html
    // 选取的k表示最后一次被打破，所以就是k * 左边填充的1 * 右边填充的1
    // time:O(n^3) space:O(n^2)
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = arr[n + 1] = 1;
        int[][] memo = new int[n + 2][n + 2];
        return helper(1, n, arr, memo);
    }

    private int helper(int i, int j, int[] nums, int[][] memo) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        int res = Integer.MIN_VALUE;
        for (int x = i; x <= j; x++) {
            res = Math.max(memo[i][j], helper(i, x - 1, nums, memo)
                + nums[i - 1] * nums[x] * nums[j + 1]
                + helper(x + 1, j, nums, memo));
        }
        memo[i][j] = res;
        return res;
    }
}