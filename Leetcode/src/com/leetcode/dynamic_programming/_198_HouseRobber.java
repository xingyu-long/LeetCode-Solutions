package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 07/18/2020
 * @Description: 时间顺序DP
 **/
public class _198_HouseRobber {

    // time:O(n) space:O(n)
    // recursion + memo
    // 利用坐标来决定位置，并且也个也会遍历各种情况，
    // 往前往后遍历应该都一样
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return helper(nums, nums.length - 1, memo);
    }

    public int helper(int[] nums, int n, int[] memo) {
        if (n < 0) {
            return 0;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int rob = helper(nums, n - 2, memo) + nums[n];
        int notRob = helper(nums, n - 1, memo);
        memo[n] = Math.max(notRob, rob);
        return memo[n];
    }

    // dp[i] 表示对于当前点偷与不偷的值
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][2];
        int res = 0;
        dp[0][0] = nums[0]; // rob
        res = dp[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        
        return res;
    }

    // space:O(n)
    // 表示为改点偷与不偷的问题
    // dp[i]表示第i点最大可以获得的利益。
    public int rob2(int[] nums) {
        int n = nums.length;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化0和1的问题
            int rob = nums[i] + (i >= 2 ? dp[i - 2] : 0);
            int not = (i >= 1) ? dp[i - 1] : 0;
            dp[i] = Math.max(rob, not);
        }
        return dp[n - 1];
    }

    // time: O(n) space: O(1)
    // https://www.youtube.com/watch?v=-i2BFAU25Zk
    public int rob3(int[] nums) {
        int notRob = 0; // 表示当前这个值不偷，保留前面最大的情况
        int rob = 0; // 表示当前这个值偷，并且需要加上前面最大的。

        for (int num : nums) {
            int temp = Math.max(notRob, rob);
            rob = notRob + num;
            notRob = temp;
        }
        return Math.max(rob, notRob);
    }

    // 用来理解上面的 dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]).
    public int rob4(int[] nums) {
        int n = nums.length;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dp = 0;
        int dpMinus1 = 0;
        int dpMinus2 = 0;
        for (int num : nums) {
            dp = Math.max(dpMinus1, dpMinus2 + num);
            dpMinus2 = dpMinus1;
            dpMinus1 = dp;
        }
        return dp;
    }
}
