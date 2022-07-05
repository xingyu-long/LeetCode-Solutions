package com.leetcode.dynamicProgramming.rangeDP;


import java.util.Arrays;


public class _1043_PartitionArrayforMaximumSum {

    // time: O(n * k) space: O(n)
    public int maxSumAfterPartitioning(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] memo = new int[arr.length + 1];
        Arrays.fill(memo, -1);
        return dfs(arr, k, 0, memo);
    }
    
    public int dfs(int[] arr, int k, int index, int[] memo) {
        if (index >= arr.length) {
            return 0;
        }
        if (memo[index] != -1) {
            return memo[index];
        }
        int res = 0;
        int max = arr[index];
        for (int i = 0; i < k; i++) {
            if (index + i >= arr.length) break;
            max = Math.max(max, arr[index + i]);
            res = Math.max(res, (i + 1) * max + dfs(arr, k, index + i + 1, memo));
        }
        memo[index] = res;
        return res;
    }
}
