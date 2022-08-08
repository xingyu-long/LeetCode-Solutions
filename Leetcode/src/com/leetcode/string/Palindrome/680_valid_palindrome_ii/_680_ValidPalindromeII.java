package com.leetcode.string.Palindrome;

/**
 * @Date: 05/09/2020
 * @Description: two pointer, Palindrome
 **/
public class _680_ValidPalindromeII {

    // 相当于一遇到不匹配的情况，就向左或者向右match一次全部。
    // time:O(n) space:O(1)
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (match(s, left + 1, right) || match(s, left, right - 1)) {
                    return true;
                } else {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean match(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
