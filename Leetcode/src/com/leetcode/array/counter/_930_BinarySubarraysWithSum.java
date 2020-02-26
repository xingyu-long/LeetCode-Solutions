package com.leetcode.array.counter;

/**
 * When: 02/24/2020
 */
public class _930_BinarySubarraysWithSum {

    // time:O(n) space:O(1)
    public int numSubarraysWithSum(int[] A, int S) {
        if (A == null || A.length == 0)
            return 0;
        return atMost(A, S) - atMost(A, S - 1);
    }

    public int atMost(int[] nums, int target) {
        if (target < 0)
            return 0;
        int start = 0, end = 0, sum = 0, n = nums.length;
        int res = 0;
        while (end < n) {
            sum += nums[end];
            while (sum > target) {
                sum -= nums[start];
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}
