package com.leetcode.backtracking;

/**
 * _ RecursiveDigitSum
 */
public class _RecursiveDigitSum {

    // hackerRank
    static int superDigit(final String n, final int k) {
        if (n == null || n.length() == 0)
            return 0;
        long sum = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            sum += n.charAt(i) - '0';
        }
        sum = sum * k;
        return helper(sum);
    }

    public static int helper(final long sum) {
        if (sum < 10) return (int) sum;
        else {
            return helper(sum % 10 + helper(sum / 10));
        }
    }
}