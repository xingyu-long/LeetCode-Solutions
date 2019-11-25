package com.leetcode.dynamicProgramming;

public class _44_WildcardMatching {

    /**
     * 44. Wildcard Matching

     DP问题，需要找到
     state（状态值） dp[m+1][n+1] dp[i][j] 表示s字符串从0到i的字符串是否和p字符串从0到j相同
     init(初始化) dp[0][0] true，因为两者都是空字符串，以及初始化第一列（p中可能有*的情况，就承接上一个情况）
     return（返回值）dp[m][n]
     func(转移方程)
     针对于(1) si=0, pi(1 <= n): dp[pi][0] = dp[pi - 1][0]
          (2) s.charAt(si-1) == s.charAt(pi-1) || s.charAt(pi - 1) == '?': 因为?匹配一个字符，
            所以这时候我们也只看前面的情况。dp[pi][si] = dp[pi - 1][si - 1];
          (3) p.charAt(pi-1) == '*' （这里的*可以是空串或者是多个）
                (3.1) 空串的话，dp[pi - 1][si]. eg:ab, ab*
                (3.2) sequence的话，dp[pi][si - 1]（这个需要再理理） eg:abcd, a*
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true; // 相当于""对应"";
        for (int pi = 1; pi <= n; pi++) { // 初始化第一列
            if (p.charAt(pi - 1) == '*') { // ? 根本和空字符串匹配不上，所以不考虑*
                dp[pi][0] = dp[pi - 1][0];
            }
        }

        for (int pi = 1; pi <= n; pi++) {
            for (int si = 1; si <= m; si++) {
                // 当前的两个字符串匹配，结果取决于前面
                if (s.charAt(si - 1) == p.charAt(pi - 1) || p.charAt(pi - 1) == '?') {
                    dp[pi][si] = dp[pi - 1][si - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    dp[pi][si] = dp[pi - 1][si] || dp[pi][si - 1]; // 前面的表示空串的时候，后面这个是any sequence
                }
            }
        }
        return dp[n][m];
    }
}
