/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 12/26/2020 09:28:28
 * @Description:  DP, DFS
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;

public class _91_DecodeWays {
    /**
     * 123 1, 2, 3 -> 第一种 12, 3 -> 第二种 1, 23 -> 第三种 res = 3;
     * <p>
     * dp[0] = 1; dp[1] = 1; i = 2 i <= 3 first -> '2' second -> '12' dp[2] = dp[0]
     * + dp[1] = 2种 i = 3, i <= 3 first -> '3' second -> '23' dp[3] = dp[1] + dp[2]
     * = 3种
     */
    // dp[i]代表前i个字符组成的子串的解码个数
    // 结果就是[i-1]即保持当前一个数的情况，那就是[i-1]的结果 以及 保持两个数那就看[i - 2]的结果。类似于climbing stairs.
    // time:O(n) space:O(n) Bottom-up; DP
    public int numDecodings(String s) {
        // DP如何找出来？状态转移方程呢？
        // 只能使用两位数来衡量并且小于26
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // 对应上s的第0位。
        for (int i = 2; i <= n; i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
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
    // time:O(n)
    public static int numDecodings2(String s) {
        int n = s.length();
        if (s == null || n == 0)
            return 0;

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(s, 0, memo);
    }

    public static int dfs(String s, int start, int[] memo) {
        if (start > s.length())
            return 0;
        if (start == s.length())
            return 1;
        if (memo[start] != -1)
            return memo[start];
        // move one char
        int res = 0;

        if (start + 1 <= s.length()) {
            int oneNum = Integer.parseInt(s.substring(start, start + 1));
            if (oneNum >= 1 && oneNum <= 9) {
                res += dfs(s, start + 1, memo);
            }
        }

        // move two char
        if (start + 2 <= s.length()) {
            int twoNum = Integer.parseInt(s.substring(start, start + 2));
            if (twoNum >= 10 && twoNum <= 26) {
                res += dfs(s, start + 2, memo);
            }
        }
        memo[start] = res;
        return memo[start];
    }
}