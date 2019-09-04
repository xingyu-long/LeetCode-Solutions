package com.leetcode.dynamicProgramming;

public class _91_DecodeWays {
    // dp[i]代表前i个字符组成的子串的解码个数
    /**
     *  91. Decode Ways
     *  When:2019/8/1
     *  Difficulty: Medium

     123
     1, 2, 3 -> 第一种
     12, 3 -> 第二种
     1, 23 -> 第三种
     res = 3;

     dp[0] = 1;
     dp[1] = 1;
        i = 2 i <= 3
        first -> '2'
        second -> '12'
        dp[2] = dp[0] + dp[1] = 2种
        i = 3, i <= 3
        first -> '3'
        second -> '23'
        dp[3] = dp[1] + dp[2] = 3种
     */
    // time:O(n) space:O(n)
    public int numDecodings(String s) {
        // DP如何找出来？状态转移方程呢？
        // 只能使用两位数来衡量并且小于26
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= len; i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }
}