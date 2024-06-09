package com.intern.Amazon;

public class LongestPalindromicSubstring {
    /**
     * 5. Longest Palindromic Substring
     * When:2019/8/25
     * difficulty: Medium
     * @param s
     * @return
     */

    // dp数组的含义是指从i到j是否为回文
    // time:O(n^2) space:O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // j - i <= 2防止后面的i+1和j-1出现逆转的情况
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
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
    String res;
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        res = "";
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }

    public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }

    public static void main(String[] args) {
        String s = "01234";
        System.out.println(s.substring(1,1));
    }
}