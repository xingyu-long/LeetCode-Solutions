package com.leetcode.array;

public class _674_LongestContinuousIncreasingSubsequence {
    // time:O(n) space:O(1)
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                max++;
                res = Math.max(res, max);
            } else {
                max = 1;
            }
        }
        return res;
    }
}
