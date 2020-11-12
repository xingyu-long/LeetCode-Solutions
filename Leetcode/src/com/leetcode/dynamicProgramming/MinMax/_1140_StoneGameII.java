package com.leetcode.dynamicProgramming.MinMax;

import java.util.Arrays;

public class _1140_StoneGameII {
    int[] prefix;
    int[][] memo;
    // time: O(n^3)
    public int stoneGameII(int[] piles) {
        // 计算相对diff
        int n = piles.length;
        prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + piles[i - 1];
        }

        memo = new int[n + 1][n + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        int diff = dfs(piles, 0, 1);
        int total = prefix[n];
        return (total + diff) / 2;
    }

    public int dfs(int[] piles, int index, int m) {
        if (index >= piles.length) return 0;
        m = Math.min(m, piles.length);
        if (index + 2 * m >= piles.length) {
            // 可以一下取完
            return prefix[piles.length] - prefix[index];
        }
        if (memo[index][m] != -1) return memo[index][m];

        int res = Integer.MIN_VALUE;
        int curr = 0;
        for (int i = 1; i <= 2 * m; i++) {
            curr += piles[index + i - 1];
            res = Math.max(res, curr - dfs(piles, index + i, Math.max(m, i)));
        }
        memo[index][m] = res;
        return res;
    }
}
