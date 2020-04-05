/*
 * @Date: 2020-04-04 14:25:56
 * @LastEditors: Clark long
 * @LastEditTime: 2020-04-04 14:41:03
 */
package com.leetcode.string.Palindrome;

public class _1400_ConstructKPalindromeStrings {
    // 是考虑用奇数和偶数的个数来做，但是当时没有讨论清楚！
    // 只需要考虑奇数的情况并且前面总长度大于k就说明可以拆分
    // time:O(n) space:O(1)
    public boolean canConstruct(String s, int k) {
        // 有很多edge case需要考虑 考虑太多了当时
        if (s.length() < k)
            return false;
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) odd++;
        }
        return odd <= k;
    }
}
