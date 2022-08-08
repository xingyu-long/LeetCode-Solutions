package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _664_StrangePrinter {
    
    /**
     * When: 03/05/2020
     * @param s
     * @return
     */
    // time:O(n^3)  space:O(n^2)
    // 也是选择哪个点 分割的问题
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] memo = new int[n + 1][n + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(s, 0, n - 1, memo);
    }
    
    public int dfs(String s, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        // 相当于首先是i位置的字符，设置为全部。
        int res = dfs(s, i + 1, j, memo) + 1;
        // 找和第一个字符相同的后面的地方
        for (int k = i + 1; k <= j; k++) {
            if (s.charAt(k) == s.charAt(i)) {
                res = Math.min(res, dfs(s, i + 1, k - 1, memo) 
                               + dfs(s, k, j, memo));
            }
        }
        memo[i][j] = res;
        return res;
    }
}