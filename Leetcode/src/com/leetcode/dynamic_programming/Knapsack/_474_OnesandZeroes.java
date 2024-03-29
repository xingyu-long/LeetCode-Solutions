package com.leetcode.dynamic_programming.Knapsack;

public class _474_OnesandZeroes {

    /**
     * When: 02/29/2020, 03/09/2020
     * @param strs
     * @param m
     * @param n
     * @return
     */

    // 反着的背包问题，这样才不会重复。
    // 利用多个0和1能构成的字符串的最大个数（这里应该是+1）
    // time:O(~ N * m * n)
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0')
                    zeros++;
                else
                    ones++;
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        _474_OnesandZeroes test = new _474_OnesandZeroes();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 4;
        int n = 3;
        System.out.println(test.findMaxForm(strs, m, n));
    }
}
