package com.leetcode.binarySearch;

import java.util.Arrays;

public class _410_SplitArrayLargestSum {
    //    410. Split Array Largest Sum
    // 先讨论如果m=1说明没有分割的情况就是全部加起来
    // m = len(nums) 则全部分割，这样求单个的max即可。
    // 然后left和right分别设置为max, sum. 最后检查的时候检查左边再右边。
    // time: O(log(sum(nums))*n)
    // space:O(1)
    public int splitArray(int[] nums, int m) {
        // min the largest sum.
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int) sum;
        int n = nums.length;
        if (m == n) return max;
        long left = max;
        long right = sum;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (isValid(mid, nums, m)) right = mid;
            else left = mid;
        }
        if (isValid(left, nums, m)) return (int) left;
        else return (int) right;
    }

    public boolean isValid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            if (total + num > target) {
                total = num;
                count++;
                if (count > m) return false;
            } else {
                total += num;
            }
        }
        return true;
    }

    // recursion + memo
    // time:O(m^2 * n) space:O(m * n)
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        return helper(nums, m, n, memo, prefix);
    }

    // 更小的元素划分，跟小的组数划分。
    // 看m-1个划分的情况。
    public int helper(int[] nums, int m, int n, int[][] memo, int[] prefix) {
        if (m == 1) return prefix[n];
        if (m > n) return Integer.MAX_VALUE;
        if (memo[m][n] != Integer.MAX_VALUE) return memo[m][n];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // 因为结尾最少都有一个元素。
            res = Math.min(res, Math.max(helper(nums, m - 1, i, memo, prefix), prefix[n] - prefix[i]));
        }
        memo[m][n] = res;
        return memo[m][n];
    }
    // bottom up dp


}
