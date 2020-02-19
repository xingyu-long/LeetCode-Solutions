package com.leetcode.string.Palindrome;

public class _5_LongestPalindromicSubstring {
    /**
     * 5. Longest Palindromic Substring
     * When:2019/8/25
     * difficulty: Medium
     * review1:11/3/2019
     *
     * @param s
     * @return
     */

    //dp[i, j] = true  if i == j
    //
    //         = s[i] == s[j]   if j = i + 1,2都可以 因为距离为1，直接s[i]s[j]比较，如果是2的话也只需要比较这个因为中间那个的值不影响。
    //
    //         = s[i] == s[j] && dp[i + 1][j - 1]   if j > i + 3
    // dp数组的含义是指从i到j是否为回文
    // time:O(n^2) space:O(n^2)
    public String longestPalindrome(String s) {
        // 其实这个也跟暴力一样，去看完所有可能的 substring。
        if (s == null || s.length() == 0) return "";
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // j - i <= 1 表示区间在2以内的时候前面(s.charAt(i) == s.charAt(j))就可以判断了
                // 但是如果大于2就需要前后移动来确定！
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
        if (s == null || s.length() == 0) return "";
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
        System.out.println(s.substring(1, 1));
    }
}