package com.leetcode.dynamic_programming.min_max;

import java.util.Arrays;

public class _1563_Stone_Game_V {
    int[] prefix;
    int[][] memo;
    // 这个题不算是min max，只是Alice单独拿，bob只是计算，然后选择下一步的方向。
    public int stoneGameV(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0) {
            return 0;
        }

        int n = stoneValue.length;
        memo = new int[n + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stoneValue[i - 1];
        }

        return dfs(stoneValue, 0, n - 1);
    }

    public int dfs(int[] stone, int left, int right) {
        if (left == right) {
            return 0;
        }
        if (memo[left][right] != -1) return memo[left][right];

        int res = 0;
        for (int k = left; k < right; k++) {
            int leftSum = prefix[k + 1] - prefix[left];
            int rightSum = prefix[right + 1] - prefix[k + 1];
            if (leftSum > rightSum) {
                res = Math.max(res, rightSum + dfs(stone, k + 1, right));
            } else if (leftSum < rightSum) {
                res = Math.max(res, leftSum + dfs(stone, left, k));
            } else {
                res = Math.max(res, leftSum + Math.max(dfs(stone, left, k), dfs(stone, k + 1, right)));
            }
        }
        memo[left][right] = res;
        return res;
    }
}
