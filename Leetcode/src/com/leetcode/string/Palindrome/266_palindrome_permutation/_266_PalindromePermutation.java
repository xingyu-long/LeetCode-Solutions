package com.leetcode.string.Palindrome;

import java.util.HashSet;

/**
 * _266_PalindromePermutation
 */
public class _266_PalindromePermutation {
    // 利用hashSet
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
            } else {
                set.add(ch);
            }
        }
        return set.size() <= 1;// 因为全是偶数的话，就会为0。只有一个奇数的话，就会为1
    }
}