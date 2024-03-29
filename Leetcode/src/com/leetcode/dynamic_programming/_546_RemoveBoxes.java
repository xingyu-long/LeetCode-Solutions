/*
 * @Date: 08/14/2021 16:20:40
 * @LastEditTime: 08/14/2021 16:21:27
 * @Description: 3d DP
 */
package com.leetcode.dynamic_programming;

public class _546_RemoveBoxes {
    // time: O(n^4), space: O(n^3)
    // 需要再仔细看看： https://www.youtube.com/watch?v=wT7aS5fHZhs
    public int removeBoxes(int[] boxes) {

        int n = boxes.length;
        int[][][] memo = new int[n][n][n];
        return dfs(boxes, 0, n - 1, 0, memo);
    }

    private int dfs(int[] boxes, int left, int right, int k, int[][][] memo) {
        if (left > right)
            return 0;
        if (memo[left][right][k] > 0)
            return memo[left][right][k];
        memo[left][right][k] = dfs(boxes, left, right - 1, 0, memo) + (k + 1) * (k + 1);
        for (int i = left; i < right; i++) {
            if (boxes[i] == boxes[right]) {
                memo[left][right][k] = Math.max(memo[left][right][k],
                        dfs(boxes, left, i, k + 1, memo) + dfs(boxes, i + 1, right - 1, 0, memo));
            }
        }
        return memo[left][right][k];
    }
}
