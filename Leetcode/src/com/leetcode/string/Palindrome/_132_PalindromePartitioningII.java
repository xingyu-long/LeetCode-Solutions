package com.leetcode.string.Palindrome;

/**
 * 132. Palindrome Partitioning II
 */
public class _132_PalindromePartitioningII {

    // 超级经典了
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        int[] cuts = new int[n];
        
        for (int j = 0; j < n; j++) {
            int min = j;
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                    min = (i == 0) ? 0 : Math.min(cuts[j - 1] + 1, min);
                }
            }
            cuts[j] = min;
        }
        return cuts[n - 1];
    }
}