package com.leetcode.dynamicProgramming.backCheckDP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 04/28/2020
 * @Description: 往回看DP
 **/
public class _1048_LongestStringChain {
    // 其实和LIS一样的问题
    // time:O(n^2 * (lenA + lenB)) space:O(n)
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int n = words.length;
        int[] dp = new int[n];
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int res = 0;
        System.out.println();

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (isValid(words[j], words[i])) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = Math.max(dp[i], max + 1);
            // System.out.print(dp[i] + " ");
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public boolean isValid(String s, String t) {
        int[] count = new int[26];
        for (char ch : t.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            count[ch - 'a']--;
            if (count[ch - 'a'] < 0) return false;
        }
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum == 1;
    }

    // Time: O(len(words) * len(word) * len(word))
    // 优化了往前看的情况
    public int longestStrChain2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        Map<String, Integer> map = new HashMap<>();
        int n = words.length, res = 0;
        // 优化了找寻的过程
        for (String word : words) {
            int max = 0;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                max = Math.max(max, map.getOrDefault(prev, 0));
            }
            max += 1; // 加上当前为结尾的情况
            map.put(word, max);
            res = Math.max(res, max);
        }
        return res;
    }
}
