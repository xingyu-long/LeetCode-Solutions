package com.leetcode.string.slidingWindow;

public class _1208_GetEqualSubstringsWithinBudget {

    /**
     * 1208. Get Equal Substrings Within Budget
     * When:2019/9/30
     * Difficulty: medium
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    // 典型的sliding window题目
    // time:O(n) space:O(1)
    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return 0;
        int i = 0; // 慢的那个pointer
        int j = 0; // 作为前进的pointer
        int res = 0;
        while (j < s.length()) {
            maxCost -= Math.abs(s.charAt(j) - t.charAt(j));
            while (maxCost < 0) {
                // System.out.println(maxCost);
                maxCost += Math.abs(s.charAt(i) - t.charAt(i));
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
