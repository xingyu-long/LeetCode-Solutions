package com.leetcode.dynamicProgramming;

public class _416_PartitionEqualSubsetSum {
    /**
     * 416. Partition Equal Subset Sum
     * When:2019/8/20
     * review1:2019/10/6
     * Difficulty: Medium
     * solution: DP 表示是否可以由前i个数构成sum/2，并且数组和不能为奇数，这样无法partition
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum /= 2;

        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // initialization
        dp[0][0] = true;

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }

        // transition function
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    // 利用1d
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                // 这里需要倒着算，是防止dp[i-num]会直接获取到刚刚变为true的情况。如果不了解可以跑一个test case
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[sum];
    }
}