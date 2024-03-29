package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 08/02/2020
 * @Description: DP
 **/
public class _1000_MinimumCosttoMergeStones {
    // N^3 / K.
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) return -1;
        int[][] memo = new int[n][n];
        int Inf = Integer.MAX_VALUE / 2;
        for (int[] arr : memo) {
            Arrays.fill(arr, Inf);
        }
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }

        return dfs(stones, prefix, memo, K, 0, n - 1, Inf);
    }

    public int dfs(int[] stones, int[] prefix, int[][] memo, int K, int i, int j, int Inf) {
        // 因为合成的堆数是一定的，所以不用考虑K的限制
        int len = j - i + 1;
        if (len < K) return 0;
        if (memo[i][j] != Inf) return memo[i][j];
        int res = Inf;
        for (int m = i; m < j; m += K - 1) {
            // 左边分为一组，右边分为K-1组
            res = Math.min(res, dfs(stones, prefix, memo, K, i, m, Inf) + dfs(stones, prefix, memo, K, m + 1, j, Inf));
        }

        if ((len - 1) % (K - 1) == 0) {
            res += prefix[j + 1] - prefix[i];
        }
        memo[i][j] = res;
        return res;
    }
}
