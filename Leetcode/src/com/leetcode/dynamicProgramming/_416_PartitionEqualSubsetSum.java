/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 11/27/2020 11:50:02
 * @Description: 0/1 knapsack
 */
package com.leetcode.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class _416_PartitionEqualSubsetSum {
    // DP 表示是否可以由前i个数构成sum/2，并且数组和不能为奇数，这样无法partition
    // 算是背包问题？
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                // 这里用i-1其实就是不用当前这个数，相当于每个元素我们只能用一次。
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];
    }

    // 利用1d
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0)
            return false;
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

    public boolean canPartition3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0)
            return false;
        int target = sum / 2;
        Map<String, Boolean> memo = new HashMap<>();
        return dfs(nums, 0, target, memo);
    }

    public boolean dfs(int[] nums, int index, int target, Map<String, Boolean> memo) {
        if (target == 0) {
            return true;
        }

        if (index >= nums.length) {
            return false;
        }

        String key = index + " - " + target;
        if (memo.containsKey(key))
            return memo.get(key);
        for (int i = index; i < nums.length; i++) {
            if (target >= nums[i]) {
                if (dfs(nums, i + 1, target - nums[i], memo)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }
        memo.put(key, false);
        return false;
    }
}