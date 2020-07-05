package com.leetcode.dynamicProgramming;

public class _44_WildcardMatching {

    /**
     *
     * When:03/15/2020
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
        int lenP = p.length(), lenS = s.length();
        boolean[][] dp = new boolean[lenP + 1][lenS + 1];
        dp[0][0] = true;
        for (int i = 1; i <= lenP; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i <= lenP; i++) {
            for (int j = 1; j <= lenS; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[lenP][lenS];
    }
}
