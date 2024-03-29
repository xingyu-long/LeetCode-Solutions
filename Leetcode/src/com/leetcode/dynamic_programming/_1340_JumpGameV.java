package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 04/26/2020
 * @Description: DP
 **/
public class _1340_JumpGameV {

    //time:O(n) space:O(n)
    public int maxJumps(int[] arr, int d) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(arr, i, d, memo));
        }
        return res;
    }

    public int dfs(int[] arr, int index, int d, int[] memo) {
        if (memo[index] != -1) {
            return memo[index];
        }
        int val = 1;
        // 都是去看比它小的情况，并且这里的memo和visited一样的效果。
        for (int i = index + 1; i <= Math.min(arr.length - 1, index + d) && arr[index] > arr[i];
            i++) {
            val = Math.max(val, dfs(arr, i, d, memo) + 1);
        }

        for (int i = index - 1; i >= Math.max(0, index - d) && arr[index] > arr[i]; i--) {
            val = Math.max(val, dfs(arr, i, d, memo) + 1);
        }
        memo[index] = val;
        return val;
    }
}
