package com.leetcode.string;

public class _678_ValidParenthesisString {
    // dfs
    // time:O(3^n)
    public boolean checkValidString(String s) {
        // how to handle *
        return dfs(s, 0, 0);
    }

    // count代表左括号的数量。
    // 遇到*就有三种不同的情况，空，左括号，右括号。
    public boolean dfs(String s, int start, int count) {
        if (count < 0) return false;// 表示 ‘(’ 过多
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count <= 0) return false;
                count--;
            } else {
                return dfs(s, i + 1, count) || dfs(s, i + 1, count + 1) || dfs(s, i + 1, count - 1);
            }
        }
        return count == 0;
    }

    //time:O(n) space:O(1)
    // 感觉这个更好懂一些。
    public boolean checkValidString2(String s) {
        int left = 0;
        int right = 0;
        int n = s.length();
        // 把*都作为 '('
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') left++;
            else left--;
            if (left < 0) return false;
        }
        if (left == 0) return true;
        // 把*作为 ')'
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') right++;
            else right--;
            if (right < 0) return false;
        }
        return true;
    }
}
