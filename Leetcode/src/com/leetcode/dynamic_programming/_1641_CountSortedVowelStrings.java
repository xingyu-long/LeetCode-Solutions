/*
 * @Date: 11/04/2020 19:50:50
 * @LastEditTime: 01/17/2021 11:07:45
 * @Description: Backtracking/ DP
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _1641_CountSortedVowelStrings {

    private char[] vowels;
    
    public int countVowelStrings(int n) {
        vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        return dfs(n, 0, '0');
    }
    
    private int dfs(int n, int count, char prev) {
        if (count == n) {
            return 1;
        }
        
        int res = 0;
        for (int i = 0; i < vowels.length; i++) {
            if (vowels[i] >= prev) {
                res += dfs(n, count + 1, vowels[i]);
            }
        }
        return res;
    }
    
    public int countVowelStrings2(int n) {
        int[][] memo = new int[n + 1][5 + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        return dfs(n, 5, memo);
    }

    private int dfs(int n, int k, int[][] memo) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (memo[n][k] != -1)
            return memo[n][k];
        int res = 0;
        // 从最后一位往第一位看
        // 相当于每层递归下去，能接上的数 越来越少
        for (int j = k; j >= 1; j--) {
            res += dfs(n - 1, j, memo);
        }
        memo[n][k] = res;
        return res;
    }
}
