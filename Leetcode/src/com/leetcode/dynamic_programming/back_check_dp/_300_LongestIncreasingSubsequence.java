package com.leetcode.dynamic_programming.back_check_dp;

public class _300_LongestIncreasingSubsequence {

    public int res = 0;

    // backtracking time:O(2^n) TLE -> 改为有返回值的情况
    public void dfs(int[] nums, int index, int level, int prev) {
        if (index == nums.length) {
            res = Math.max(res, level);
            return;
        }

        // 取当前这个数
        if (nums[index] > prev) {
            dfs(nums, index + 1, level + 1, nums[index]);
        }
        // 不取当前这个数
        dfs(nums, index + 1, level, prev);
    }
    // recursive way TLE
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }

    // https://www.youtube.com/watch?v=fV-TF4OvZpk
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
    // 记得要画图演示，往回看DP
    // time:O(n^2) space:O(n)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int max = 1;// 每次默认自己算为1
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > max) {
                    max = dp[j] + 1;
                }
            }
            dp[i] = max;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 利用了binary search，相当于在这个途中构造有序的序列
    // 这个解法比较酷
    // time: O(nlogn) space: O(n)
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        int[] sorted = new int[nums.length];
        sorted[res++] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sorted[res - 1]) {
                sorted[res++] = nums[i];
            } else {
                int index = findIndex(sorted, 0, res - 1, nums[i]);
                sorted[index] = nums[i];
            }
        }
        return res;
    }

    public int findIndex(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target > nums[right]) return right + 1;
        if (target <= nums[left]) return left;
        else return right;
    }
}
