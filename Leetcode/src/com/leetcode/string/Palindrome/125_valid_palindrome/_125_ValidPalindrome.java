package com.leetcode.string.Palindrome;

/**
 * @Date: 2019/7/17, 05/09/2020
 * @Description: two pointer, Palindrome
 **/
public class _125_ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        //然后使用two pointer
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            // 去除其他非数字或者字母的情况
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))) {
                lo++;
            }
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))) {
                hi--;
            }
            if (Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi))) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}
