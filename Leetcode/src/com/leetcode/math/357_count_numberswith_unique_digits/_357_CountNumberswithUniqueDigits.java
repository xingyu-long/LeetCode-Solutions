package com.leetcode.math;

/**
 * _357_CountNumberswithUniqueDigits
 */
public class _357_CountNumberswithUniqueDigits {

    /**
     * 一位数: 10个
     * 两位数: 9(1-9选一个) * 9(除去前面的一个，但是包括0，所以还是9种可能)
     * 三位数：9 * 9 * 8 
     * 都是累加。
     * @param n
     * @return
     */
    // time:O(n) space:O(n) 
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 10;
        int digit = 9;
        int k = 9;
        for (int i = 2; i <= n; i++) {
            if (k > 0) {
                digit *= k;
                k--;
                dp[i] = dp[i - 1] + digit;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }

    // time:O(min(n, 10)) space: O(1)
    public int countNumbersWithUniqueDigits2(int n) {
        if (n < 1) return 1;
        int res = 10;
        int digit = 9;
        int k = 9;
        for (int i = 2; i <= n && k > 0; i++) {
            digit *= k;
            k--;
            res = res + digit;
        }
        return res;
    }
}