package com.intern.Amazon.VO;

public class _53_ {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
