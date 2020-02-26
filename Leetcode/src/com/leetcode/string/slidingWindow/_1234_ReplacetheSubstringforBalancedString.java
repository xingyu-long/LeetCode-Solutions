package com.leetcode.string.slidingWindow;

/**
 * When: 02/25/2020
 */
public class _1234_ReplacetheSubstringforBalancedString {

    // time:O(n) space:O(1)
    public int balancedString(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length(), start = 0, end = 0;
        int[] count = new int[128];
        int target = n / 4, res = n;
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)]++;
        }
        while (end < n) {
            count[s.charAt(end)]--;// 
            // 相当于看 在两个pointer外面的是否是balanced。
            while (start < n && count['Q'] <= target && 
                  count['W'] <= target && count['E'] <= target && 
                   count['R'] <= target) {
                res = Math.min(res, end - start + 1);
                count[s.charAt(start)]++;
                start++;
            } 
            end++;
        }
        return res;
    }
}