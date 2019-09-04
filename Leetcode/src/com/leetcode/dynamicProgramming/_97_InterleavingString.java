package com.leetcode.dynamicProgramming;

public class _97_InterleavingString {
    /**
     *  97. Interleaving String
     *  When:2019/8/1
     *  Difficulty: Hard

     这里的true表示可以达到的意思，然后依次检查就是s1和s2中
     */
    // time:O(m * n) space:O(m * n)
    public boolean isInterleave(String s1, String s2, String s3) {
        if ((s1.length() + s2.length()) != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s2.length() + 1][s1.length() + 1];
        dp[0][0] = true;

        //初始化第一列和第一行，找寻与s3中相等的情况
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        // 为true的话表示这个点在s3中有对应，并且看最后个点判断是否interleave
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = (dp[i - 1][j] && s2.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s1.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s2.length()][s1.length()];
    }
}
