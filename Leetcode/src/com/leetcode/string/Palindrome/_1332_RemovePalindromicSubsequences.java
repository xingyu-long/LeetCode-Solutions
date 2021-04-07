/*
 * @Date: 01/26/2020 09:28:19
 * @LastEditTime: 03/08/2021 09:00:55
 * @Description: Palindrome
 */
package com.leetcode.string.Palindrome;

public class _1332_RemovePalindromicSubsequences {
    // time:O(n) space:O(1)
    // 题目理解比较trick。
    public int removePalindromeSub(String s) {
        // 主要这里的subsequence的定义，可以通过删除一些字符组成，不是substring(这个是要连续的)
        if (s == null || s.length() == 0)
            return 0;
        if (isValid(s))
            return 1;
        return 2;// 先移除所有a，然后移除所有b。
    }

    public boolean isValid(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}
