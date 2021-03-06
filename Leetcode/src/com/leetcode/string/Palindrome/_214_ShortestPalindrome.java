package com.leetcode.string.Palindrome;

import java.util.Collections;

public class _214_ShortestPalindrome {

    /**
     * 214. Shortest Palindrome
     * When:2019/8/8
     * Difficulty: Hard
     * 只能插入在前面
     *
     * @param s
     * @return
     */

    // time:O(n^2) 里面的substring操作 space:O(n)
    // 先反转字符串，然后找相同共同的位置，说明这一段属于回文，前面需要加上[0,i] 区间的
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder(s);
        String reverse = sb.reverse().toString();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.substring(0, n - i).equals(reverse.substring(i))) {
                return reverse.substring(0, i) + s;
            }
        }
        return "";
    }

    public static String shortestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        int i = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) i++;
        }
        if (i == n) return s;//表示该字符串符合回文
        String remain = s.substring(i);
        String reverseRemain = new StringBuilder(remain).reverse().toString();
        return reverseRemain + shortestPalindrome2(s.substring(0, i)) + remain;
    }

    public static void main(String[] args) {
        System.out.println(shortestPalindrome2("abbac"));
    }
}