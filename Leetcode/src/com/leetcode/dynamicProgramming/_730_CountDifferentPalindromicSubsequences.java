/*
 * @Date: 07/26/2022 10:51:19
 * @LastEditTime: 07/26/2022 10:51:19
 * @Description: You need to fill out
 */

package com.leetcode.dynamicProgramming;
 
public class _730_CountDifferentPalindromicSubsequences {
    int MOD = (int) Math.pow(10, 9) + 7;
    
    // time: O(n ^ 2)
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return dfs(s.toCharArray(), 0, n - 1, memo);
    }
    
    public int dfs(char[] chs, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] > 0) return memo[i][j];
        
        long res = 0;
        if (chs[i] == chs[j]) {
            // 如何去重，这里比较难
            res += dfs(chs, i + 1, j - 1, memo) * 2;
            int left = i + 1, right = j - 1;
            while (left <= right && chs[left] != chs[i]) left++;
            while (left <= right && chs[right] != chs[i]) right--;
            if (left > right) res += 2;
            else if (left == right) res += 1;
            else {
                res -= dfs(chs, i + 1, j - 1, memo);
            }
        } else {
            res = dfs(chs, i, j - 1, memo) + dfs(chs, i + 1, j, memo) - dfs(chs, i + 1, j - 1, memo);
        }
        memo[i][j] = (int) ((res + MOD) % MOD);
        return memo[i][j];
    }
}
