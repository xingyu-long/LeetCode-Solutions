package com.leetcode.math;

public class _264_UglyNumberII {
    /**
     * 264.Ugly Number II
     * When:2019/8/12
     * Difficulty: Medium
     * @param n
     * @return
     */
    // 利用动态规划
    public int nthUglyNumber(int n) {
        // how to get the sequence of ugly number
        int[] nums = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.min(nums[index2] * 2, Math.min(nums[index3] * 3, nums[index5] * 5));
            if (nums[i] == nums[index2] * 2) index2++;
            if (nums[i] == nums[index3] * 3) index3++;
            if (nums[i] == nums[index5] * 5) index5++;
        }
        return nums[n - 1];
    }
}
