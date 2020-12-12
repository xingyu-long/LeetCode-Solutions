/*
 * @Date: 10/07/2020 10:06:21
 * @LastEditTime: 12/12/2020 16:26:07
 * @Description: MinMax, Top-down + Memo
 */
package com.leetcode.dynamicProgramming.MinMax;

import java.util.Arrays;

public class _486_PredictTheWinner {
    int[][] memo;

    public boolean PredictTheWinner(int[] nums) {
        // 博弈的那个题，可以算相对值
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        memo = new int[n + 1][n + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        int diff = dfs(nums, 0, n - 1);
        return diff >= 0; // equal的话，就是player1赢
    }

    public int dfs(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (memo[start][end] != -1)
            return memo[start][end];
        int takeLeft = nums[start] - dfs(nums, start + 1, end);
        int takeRight = nums[end] - dfs(nums, start, end - 1);
        int res = Math.max(takeLeft, takeRight);
        memo[start][end] = res;
        return res;
    }
}
