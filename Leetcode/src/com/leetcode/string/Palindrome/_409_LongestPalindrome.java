package com.leetcode.string.Palindrome;

import java.util.HashSet;

/**
 * @Date: 2019/7/21, 05/09/2020
 * @Description: Palindrome
 **/
public class _409_LongestPalindrome {

    // 只是需要考虑有奇数个，但是大于2所以可以配对，但是palindrome肯定就只能有1个odd。
    public int longestPalindrome2(String s) {
        // 其实就是看偶数的个数。
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[128];
        for (char ch : s.toCharArray()) {
            count[ch]++;
        }
        int even = 0;
        boolean odd = false;
        for (int i = 0; i < 128; i++) {
            if (count[i] != 0) {
                if (count[i] % 2 == 0) {
                    even += count[i] / 2;
                } else if (count[i] % 2 != 0 && count[i] > 2) {
                    even += count[i] / 2;
                    odd = true;
                } else {
                    odd = true;
                }
            }
        }
        return odd ? even * 2 + 1 : even * 2;
    }

    //time:O(n) space:O(n)
    public int longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int count = 0; // for even number;
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
                count++;
            } else {
                set.add(ch);
            }
        }
        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }

    // 当然也可以用counting sort那种降低空间复杂度
    // space:O(1)
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] counter = new int[256];
        int count = 0; // for even number;
        for (char ch : s.toCharArray()) {
            if (counter[ch] > 0) {
                counter[ch]--;
                count++;
            } else {
                counter[ch]++;
            }
        }
        boolean odd = false;
        for (char ch : s.toCharArray()) {
            if (counter[ch] != 0) {
                odd = true;
            }
        }
        return odd ? count * 2 + 1 : count * 2;
    }

    public static void main(String[] args) {
        // longestPalindrome("AAAAAA");
    }
}
