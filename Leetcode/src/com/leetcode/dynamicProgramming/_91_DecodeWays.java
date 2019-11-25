package com.leetcode.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

public class _91_DecodeWays {
    /**
     * 91. Decode Ways
     * When:2019/8/1
     * review1: 11/8/2019
     * Difficulty: Medium
     * <p>
     * 123
     * 1, 2, 3 -> 第一种
     * 12, 3 -> 第二种
     * 1, 23 -> 第三种
     * res = 3;
     * <p>
     * dp[0] = 1;
     * dp[1] = 1;
     * i = 2 i <= 3
     * first -> '2'
     * second -> '12'
     * dp[2] = dp[0] + dp[1] = 2种
     * i = 3, i <= 3
     * first -> '3'
     * second -> '23'
     * dp[3] = dp[1] + dp[2] = 3种
     */
    // dp[i]代表前i个字符组成的子串的解码个数
    // 结果就是[i-1]即保持当前一个数的情况，那就是[i-1]的结果 以及 保持两个数那就看[i - 2]的结果。类似于climbing stairs.
    // time:O(n) space:O(n) Bottom-up; DP
    public int numDecodings(String s) {
        // DP如何找出来？状态转移方程呢？
        // 只能使用两位数来衡量并且小于26
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // 对应上s的第0位。
        for (int i = 2; i <= n; i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = (s.charAt(i - 2) - '0') * 10 +
                    (s.charAt(i - 1) - '0');
            if (oneDigit > 0 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
}

    // DFS + memo
    public static int numDecodings2(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return dfs2(s, 0, dp);
    }

    public static int dfs2(String s, int start, int[] dp) {
        if (start >= s.length()) return 1;
        if (s.charAt(start) == '0') return 0;
        if (dp[start] > -1) return dp[start];
        int ways = 0;
        // for one digit 因为前面check了start肯定小于s.length()
        int oneDigit = s.charAt(start) - '0';
        if (oneDigit > 0 && oneDigit <= 9) {
            ways += dfs2(s, start + 1, dp);
        }

        // for two digit
        if (start + 1 < s.length()) {
            int twoDigits = (s.charAt(start) - '0') * 10 +
                    (s.charAt(start + 1) - '0');
            if (twoDigits >= 10 && twoDigits <= 26) {
                ways += dfs2(s, start + 2, dp);
            }
        }
        dp[start] = ways;
        return ways;
    }

    public static void main(String[] args) {
        numDecodings2("0");
    }
}