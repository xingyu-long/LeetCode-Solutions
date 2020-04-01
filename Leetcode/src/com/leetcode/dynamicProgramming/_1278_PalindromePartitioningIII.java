/*
 * @Date: 2020-03-31 17:33:04
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-31 17:34:56
 */
package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1278_PalindromePartitioningIII {

    //典型的分组DP问题，讨论与前一组的关系
    // time: (n * n * k * cost(这个可以用二维dp优化))
    public int palindromePartition(String s, int k) {
        int[][] memo = new int[s.length() + 1][k + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(s, s.length() - 1, k, memo);
    }
    
    public int dfs(String s, int j, int k, int[][] memo) {
        if (j + 1 == k) return 0; //j 有 j + 1个。每个字符分为一组
        if (k == 1) return cost(s, 0, j);
        if (memo[j][k] != -1) return memo[j][k];
        int res = Integer.MAX_VALUE / 2;
        for (int i = j; i >= 0; i--) {
            res = Math.min(res, dfs(s, i, k - 1, memo) + cost(s, i + 1, j));
        }
        memo[j][k] = res;
        return res;
    }
    
    
    public int cost(String s, int i, int j) {
        int cost = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) cost++;
            i++;
            j--;
        }
        return cost;
    }
}