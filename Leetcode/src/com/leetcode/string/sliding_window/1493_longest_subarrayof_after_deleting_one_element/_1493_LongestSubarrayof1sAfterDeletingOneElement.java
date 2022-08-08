package com.leetcode.string.sliding_window;

/**
 * @Date: 06/27/2020
 * @Description: TODO
 **/
public class _1493_LongestSubarrayof1sAfterDeletingOneElement {
    // time:O(n) space:O(1)
    public int longestSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // sliding window
        int start = 0, end = 0;
        int zeroCount = 0;
        int res = 0, n = nums.length;
        while (end < n) {
            if (nums[end] == 0) zeroCount++;
            while (zeroCount > 1) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
            res = Math.max(res, end - start);
            end++;
        }
        return res;
    }
}
