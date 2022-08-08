/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 06/16/2022 15:05:32
 * @Description: Palindrome, Backtracking
 */
package com.leetcode.string.Palindrome;

import java.util.ArrayList;
import java.util.List;

public class _131_PalindromePartitioning {

    // build result from begin to end;
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
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

    private void dfs(List<List<String>> res, List<String> list, int index, String s, boolean[][] dp) {
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

    // build result from end to begin.
    public List<List<String>> partition2(String s) {
        // pre-compute 
        if (s == null) return new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            dp[j][j] = true;
            for (int i = 0; i < j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
            }
        }
        List<List<String>> res = new ArrayList<>();
        return dfs2(s, dp, 0);
    }
    
    public List<List<String>> dfs2(String s, boolean[][] dp, int index) {
        List<List<String>> res = new ArrayList<>();
        if (index == s.length()) {
            List<String> temp = new ArrayList<>();
            res.add(new ArrayList<>(temp));
            return res;
        }
        
        for (int end = index + 1; end <= s.length(); end++) {
            if (dp[index][end - 1]) {
                List<List<String>> next = dfs2(s, dp, end);
                for (List<String> list : next) {
                    list.add(0, s.substring(index, end));
                    res.add(new ArrayList<>(list));
                }
            }
        }
        return res;
    }
}
