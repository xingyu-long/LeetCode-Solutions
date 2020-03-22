/*
 * @Date: 2020-03-20 17:11:06
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-20 17:13:19
 */
package com.leetcode.string.slidingWindow;

public class _713_SubarrayProductLessThanK {
    // time:O(n) space:O(1)
    // k如果小于等于1的话，没有满足的情况，因为nums是正数
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // 这个之前没有考虑到
        int start = 0, end = 0;
        int n = nums.length;
        int res = 0, prod = 1;
        // at most
        while (end < n) {
            prod *= nums[end];
            while (prod >= k) {
                prod /= nums[start];
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}