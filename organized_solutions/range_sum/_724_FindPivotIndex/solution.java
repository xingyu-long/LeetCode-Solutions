package com.leetcode.range_sum;

/**
 * @Date: 07/05/2020
 * @Description: Range Sum,
 **/
public class _724_FindPivotIndex {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n; i++) {
            if (prefix[i] == prefix[n] - prefix[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public int pivotIndex2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = 0;
        int n = nums.length;
        for (int num : nums) {
            right += num;
        }
        for (int i = 0; i < n; i++) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
