package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1048_LongestStringChain {
    // 这个题和那个Longest Increasing Subsequence DP的解法很类似，
    // 只是这里注意那个验证的function是否写对，以及我们需要其length相差1
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) return 0;
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int res = 0;
        int n = words.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (words[i].length() - words[j].length() == 1
                        && isValid(words[j], words[i])) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(dp[i], res);
        }
        return res;
    }

    // 有点类似于那个求最长的大于的数的情况
    // O(n^2)
    public boolean isValid(String w1, String w2) {
        if (w1.length() == w2.length()) return false;
        int diff = 0;
        int i = 0, j = 0;
        while (i < w1.length()) {
            if (w1.charAt(i) == w2.charAt(j)) {
                i++;
                j++;
            } else {
                diff++;
                if (diff > 1) return false;
                j++;
            }
        }
        return true;
    }
}
