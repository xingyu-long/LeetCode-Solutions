package com.leetcode.array.counter;

/**
 * When: 02/25/2020
 */
public class _1248_CountNumberofNiceSubarrays {
    // time:O(n) space:O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        if (k < 0)
            return 0;
        int res = 0;
        int start = 0, end = 0, count = 0, n = nums.length;
        while (end < n) {
            if (nums[end] % 2 != 0)
                count++;
            while (count > k) {
                if (nums[start] % 2 != 0)
                    count--;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}
