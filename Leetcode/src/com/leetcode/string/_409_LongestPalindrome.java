package com.leetcode.string;

public class _409_LongestPalindrome {

    /**
     *  409. Longest Palindrome
     *  When:2019/7/21
     *  Solution:
     *  利用hash，这里的计数不要减去'a'而是'0' 因为A 小于a 这样会产生负
     *
     *  犯的错误：
     *  1. count里面减去了'a' 导致A减去产生负数
     *  2. 循环count的时候 范围应该是128 而不是s.length()
     * @param s
     * @return
     */

    // time:O(n) space:O(1)
    public static int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        // 利用Hash的做法
        int[] count = new int[128];
        int lenOfMax = 0;
        int flag = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }
        for (int i = 0; i < 128; i++) {
            if (count[i] % 2 == 0) {
                lenOfMax += count[i];
            } else {
                lenOfMax += count[i] - 1;
                flag = 1;
            }
        }
        res = lenOfMax + flag; //如果有奇数字符，那这个flag就会为1
        return res;
    }

    // 只是需要考虑有奇数个，但是大于2所以可以配对，但是palindrome肯定就只能有1个odd。
    public int longestPalindrome2(String s) {
        // 其实就是看偶数的个数。
        if (s == null || s.length() == 0) return 0;
        int[] count = new int[128];
        for (char ch : s.toCharArray()) {
            count[ch]++;
        }
        int even = 0;
        boolean odd = false;
        for (int i = 0; i < 128; i++) {
            if (count[i] != 0) {
                if (count[i] % 2 == 0) even += count[i] / 2;
                else if (count[i] % 2 != 0 && count[i] > 2) {
                    even += count[i] / 2;
                    odd = true;
                } else {
                    odd = true;
                }
            }
        }
        return odd ? even * 2 + 1 : even * 2;
    }
    public static void main(String[] args) {
        longestPalindrome("AAAAAA");
    }
}
