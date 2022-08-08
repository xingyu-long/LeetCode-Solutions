/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 08/08/2022 16:26:06
 * @Description: Palindrome, DP
 */
package com.leetcode.string.Palindrome;

public class _5_LongestPalindromicSubstring {

    // dp数组的含义是指从i到j是否为回文
    // time:O(n^2) space:O(n^2)
    public String longestPalindrome(String s) {
        // 其实这个也跟暴力一样，去看完所有可能的 substring。
        if (s == null || s.length() == 0) {
            return "";
        }
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // j - i <= 1 表示区间在2以内的时候前面(s.charAt(i) == s.charAt(j))就可以判断了
                // 但是如果大于2就需要前后移动来确定！
                // 后面的是dp[i+1][j-1]看中间的情况，不要忘了
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 1 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    // 中心扩散法
    // time: O(n^2) space:O(1)
    // 这里考虑到 内部回文一个的话 就是 i，i开始
    // 如果回文内部是一对，则就是i, i + 1
    String res = "";

    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }

    public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }
}