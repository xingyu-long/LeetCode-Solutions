package com.leetcode.dynamic_programming;

/**
 * @Date: 05/16/2020, 09/01/2020
 * @Description: Subarray, DP
 **/
public class _918_MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int currMax = 0, max = Integer.MIN_VALUE;
        int currMin = 0, min = Integer.MAX_VALUE;
        int total = 0;
        for (int num : A) {
            currMax = Math.max(currMax + num, num);
            max = Math.max(max, currMax);

            currMin = Math.min(currMin + num, num);
            min = Math.min(min, currMin);

            total += num;
        }
        // 这个case需要考虑[-2,-3,-1]
        return max > 0 ? Math.max(max, total - min) : max; // 如果全为负数，则返回max
    }
}
