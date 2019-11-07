package com.leetcode.binarySearch;

public class _300_LongestIncreasingSubsequence {

    /**
     *  300. Longest Increasing Subsequence
     *  When:2019/7/21
     *  review1:2019/10/17
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
    // 记得要画图演示
    // time:O(n^2) space:O(n)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { //表示前面有可以选择的
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 利用了binary search，相当于在这个途中构造有序的序列
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
