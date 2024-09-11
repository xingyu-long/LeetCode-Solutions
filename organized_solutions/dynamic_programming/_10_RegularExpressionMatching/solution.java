package com.leetcode.dynamic_programming;

public class _10_RegularExpressionMatching {
    /**
     * When: 03/17/2020
     * @param s
     * @param p
     * @return
     */

     
    // time:O(lenS * lenP) space: O(lenS * lenP)
    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenP + 1][lenS + 1];
        dp[0][0] = true;
        for (int i = 1; i <= lenP; i++) {
            if (p.charAt(i - 1) == '*')
                dp[i][0] = dp[i - 2][0];// eg: 空串 和 a*
        }

        for (int pi = 1; pi <= lenP; pi++) {
            for (int si = 1; si <= lenS; si++) {
                if (p.charAt(pi - 1) == s.charAt(si - 1) || p.charAt(pi - 1) == '.') {
                    dp[pi][si] = dp[pi - 1][si - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    // 一个或者多个match的情况 其中也包括aa 和 .* 这种例子
                    if (p.charAt(pi - 2) == s.charAt(si - 1) || p.charAt(pi - 2) == '.') {
                        dp[pi][si] = dp[pi - 2][si] || dp[pi][si - 1]; // 前面是-2是因为有.*这种情况
                    } else {
                        // empty: abcd, abcde*
                        dp[pi][si] = dp[pi - 2][si];
                    }
                }
            }
        }
        return dp[lenP][lenS];
    }

}
