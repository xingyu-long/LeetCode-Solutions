package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 04/15/2020
 * @Description: DP,
 **/
public class _1130_MinimumCostTreeFromLeafValues {
    // n^3 * T(find)
    public int mctFromLeafValues(int[] arr) {
        // 题目一开始理解错了，应该是记录左右的最大值
        // 也是找分割点
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] memo = new int[n + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(arr, 0, n - 1, memo);
    }

    public int dfs(int[] nums, int i, int j, int[][] memo) {
        if (i == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            res = Math.min(res, dfs(nums, i, k, memo) + dfs(nums, k + 1, j, memo) +
                findMax(nums, i, k) * findMax(nums, k + 1, j));
        }
        memo[i][j] = res;
        return res;
    }

    public int findMax(int[] nums, int i, int j) {
        int max = nums[i];
        for (int k = i; k <= j; k++) {
            max = Math.max(max, nums[k]);
        }
        return max;
    }
}
