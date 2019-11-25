package com.leetcode.dynamicProgramming;

public class _72_EditDistance {

    /**
     *  72. Edit Distance
     *  When:2019/8/1
     *  Difficulty: Hard


     dp[i][j]表示的是，从字符串1的i的位置转换到字符串2的j的位置，所需要的最少步数。


     1,字符串中的字符相等: dp[i][j] = dp[i - 1][j - 1]
     2,字符串中的字符不等:
     insert: dp[i][j] = dp[i][j - 1] + 1;
     replace: dp[i][j] = dp[i - 1][j - 1] + 1;
     delete: dp[i][j] = dp[i - 1][j] + 1;

           a  b  c  d
        0  1  2  3  4
     a  1  0  1  2  3
     e  2  1  1  2  3
     f  3  2  2  2  3

     solution:
     https://www.youtube.com/watch?v=iHtd2KjWX_c&list=PLvyIyKZVcfAk4vxVK-QQYha7VfE4SLm9q&index=72
     * @param word1
     * @param word2
     * @return
     */
    // time:O(m * n) space:O(m * n)
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化第一行和第一列。这里没有赋值为0 是因为需要从index为1开始计算
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 什么都不需要改变
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i -1][j] + 1, dp[i - 1][j - 1] + 1));
                }
                System.out.print("dp[" + i + "][" + j + "] = " + dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        minDistance(word1, word2);
    }
}
