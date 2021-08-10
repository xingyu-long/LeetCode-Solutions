/*
 * @Date: 04/06/2020 15:19:32
 * @LastEditTime: 08/06/2021 10:20:26
 * @Description: 区间DP
 */
package com.leetcode.dynamicProgramming.MinMax;

import java.util.Arrays;

public class _877_StoneGame {
    // Time: O(N^2), Space: O(N^2)
    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) return false;
        int n = piles.length;
        int[][] memo = new int[n + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        int alexGet = dfs(piles, 0, piles.length - 1, memo);
        return alexGet > 0;
    }


    public int dfs(int[] piles, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (i == j) return piles[i];
        if (memo[i][j] != -1) return memo[i][j];
        int takeLeft = piles[i] - dfs(piles, i + 1, j, memo); // 算相对的，后面之所以会减去，就是对方选取的时候。
        int takeRight = piles[j] - dfs(piles, i, j - 1, memo);
        int res = Math.max(takeLeft, takeRight);
        memo[i][j] = res;
        return res;
    }
}
