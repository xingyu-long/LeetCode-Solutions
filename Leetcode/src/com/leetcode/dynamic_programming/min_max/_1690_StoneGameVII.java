/*
 * @Date: 12/13/2020 11:07:22
 * @LastEditTime: 12/16/2020 10:13:35
 * @Description: MinMax, top-down + memo
 */
package com.leetcode.dynamic_programming.min_max;

public class _1690_StoneGameVII {
    // Time: O(n^2)
    public int stoneGameVII(int[] stones) {
        // 主要注意的是两个其实都是为了拿到更多的情况。
        // 后面可以画一个表加深理解。
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int n = stones.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }
        int[][] memo = new int[n + 1][n + 1];
        return dfs(stones, 0, n - 1, prefix, memo);
    }

    private int dfs(int[] stones, int left, int right, int[] prefix, int[][] memo) {
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int takeLeft = prefix[right + 1] - prefix[left + 1] - dfs(stones, left + 1, right, prefix, memo);
        int takeRight = prefix[right] - prefix[left] - dfs(stones, left, right - 1, prefix, memo);
        int res = Math.max(takeLeft, takeRight);
        memo[left][right] = res;
        return res;
    }
}
