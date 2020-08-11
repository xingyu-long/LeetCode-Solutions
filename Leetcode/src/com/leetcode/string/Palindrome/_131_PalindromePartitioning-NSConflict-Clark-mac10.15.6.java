package com.leetcode.string.Palindrome;

import java.util.ArrayList;
import java.util.List;

import static com.leetcode.string.Palindrome._336_PalindromePairs.isPalindrome;

/**
 * @Date: 2019/8/9, 05/09/2020
 * @Description: Palindrome, Backtracking
 **/
public class _131_PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        helper(res, new ArrayList<>(), s, 0);
        return res;
    }

    // 利用backtracking (每次取的长度是1<=n) 然后利用index来判断是否走到了终点
    public static void helper(List<List<String>> res, List<String> list, String s, int start) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String temp = s.substring(start, i);
            if (isValid(temp)) { // 有效了我再进去下一层。
                list.add(temp);
                helper(res, list, s, i);
                list.remove(list.size() - 1);
            }
        }
    }

    public static boolean isValid(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    // 利用dp先报存其Palindrome的情况，注意下标
    public List<List<String>> partition2(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        // 先构造其pali的关系的dp数组，用backtracking去找 但是如何验证？？ 每次向前走的时候验证？
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, s, dp);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> list, int index, String s,
        boolean[][] dp) {
        if (index == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]) {
                list.add(s.substring(index, i + 1));
                dfs(res, list, i + 1, s, dp);
                list.remove(list.size() - 1);
            }
        }
    }
}
