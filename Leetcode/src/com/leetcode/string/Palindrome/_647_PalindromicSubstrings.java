package com.leetcode.string.Palindrome;

/**
 * 647. Palindromic Substrings
 */
public class _647_PalindromicSubstrings {

    // 利用DP
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // j - i <= 2 表示区间在2以内的时候前面(s.charAt(i) == s.charAt(j))就可以判断了
                // 但是如果大于2就需要前后移动来确定！
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) res++;
            }
        }
        return res;
    }
    // 利用扩散法
    // 只需要原来的基础上每次res++;即可
}