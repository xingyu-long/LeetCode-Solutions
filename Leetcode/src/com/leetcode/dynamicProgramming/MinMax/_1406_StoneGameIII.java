package com.leetcode.dynamicProgramming.MinMax;

import java.util.Arrays;

public class _1406_StoneGameIII {
    int[] memo;
    // 基本和I差不多
    public String stoneGameIII(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0) {
            return "Tie";
        }
        int n = stoneValue.length;
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int diff = dfs(stoneValue, 0);
        if (diff > 0) {
            return "Alice";
        } else if (diff < 0) {
            return "Bob";
        }
        return "Tie";
    }

    public int dfs(int[] stone, int index) {
        if (index >= stone.length) {
            return 0;
        }
        if (memo[index] != -1) return memo[index];
        int res = Integer.MIN_VALUE;
        int curr = 0;
        for (int i = index; i < index + 3 && i < stone.length; i++) {
            curr += stone[i];
            res = Math.max(res, curr - dfs(stone, i + 1));
        }
        memo[index] = res;
        return res;
    }
}
