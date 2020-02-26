package com.leetcode.string.slidingWindow;

public class _1358_NumberofSubstringsContainingAllThreeCharacters {
    // When: 2/22/2020
    // time:O(n)
    // space:O(1)
    public int numberOfSubstrings(String s) {
        // "abcabc"
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        int[] count = new int[3];
        int start = 0, end = 0;
        int n = s.length();
        while (end < n) {
            count[s.charAt(end) - 'a']++;
            // 这个边界条件也用的很妙
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                res += n - end; // 这里用的很妙，相当于是把 [start, end ~ n]这段string加起来了。
                count[s.charAt(start) - 'a']--;
                start++;
            }
            end++;
        }
        return res;
    }
}
