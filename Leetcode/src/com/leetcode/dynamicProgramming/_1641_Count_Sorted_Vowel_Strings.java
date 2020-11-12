package com.leetcode.dynamicProgramming;

import java.util.Arrays;

/**
 * @Date: 11/04/2020
 * @Description: DFS+MEMO
 **/
public class _1641_Count_Sorted_Vowel_Strings {
    public int countVowelStrings(int n) {
        int[][] memo = new int[n + 1][5 + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        return dfs(n, 5, memo);
    }

    private int dfs(int n, int k, int[][] memo) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (memo[n][k] != -1) return memo[n][k];
        int res = 0;
        // 相当于每层递归下去，能接上的数 越来越少
        for (int j = k; j >= 1; j--) {
            res += dfs(n - 1, j, memo);
        }
        memo[n][k] = res;
        return res;
    }
}
