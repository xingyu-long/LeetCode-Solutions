package com.leetcode.binarySearch;

public class _300_LongestIncreasingSubsequence {

    /**
     *  300. Longest Increasing Subsequence
     *  When:2019/7/21
     *  solution:
     *  1. DP 记得保存前面的情况
     *  2.
     * @param nums
     * @return
     */
    // recursive way
    // TLE
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthOfLIS(int[] nums, int prev, int index) {
        if (index == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[index] > prev) {
            taken = 1 + lengthOfLIS(nums, nums[index], index + 1);
        }
        int notTaken = lengthOfLIS(nums, prev, index + 1);
        return Math.max(taken, notTaken);
    }

    // Dynamic Programming
    // 记得要画图演示
    // time:O(n^2) space:O(n)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { //表示前面有可以选择的
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1; // 加入当前i这种情况
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
