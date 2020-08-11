package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1320_MinimumDistancetoTypeaWordUsingTwoFingers {

    /**
     * When: 3/8/2020, 4/24/2020
     * @param word
     * @return
     */
    // https://www.youtube.com/watch?v=GRRDl3HxQAI
    public int k;
    // 相当于说 当前的字符使用finger1还是finger2
    // time: O(n * 27 * 27) space: O(n * 27 * 27)
    public int minimumDistance(String word) {
        k = 26;// 作为初始的free状态
        int n = word.length();
        int[][][] memo = new int[n][27][27];
        for (int[][] arrs : memo) {
            for (int[] arr : arrs) Arrays.fill(arr, -1);
        }
        return dfs(word, 0, k, k, n, memo);
    }
    
    
    public int cost(int from, int to) {
        if (from == k) return 0;
        return Math.abs(from / 6 - to / 6) + Math.abs(from % 6 - to % 6);
    }
    
    // min cost to type word[i : n]. l是左手敲进去，r就是右手敲进去
    public int dfs(String word, int i, int l, int r, int n, int[][][] memo) {
        if (i == n) return 0;
        if (memo[i][l][r] != -1) return memo[i][l][r];
        int pos = word.charAt(i) - 'A';
        int res = Math.min(dfs(word, i + 1, pos, r, n, memo) + cost(l, pos), 
                           dfs(word, i + 1, l, pos, n, memo) + cost(r, pos));
        memo[i][l][r] = res;
        return res;
    }
}