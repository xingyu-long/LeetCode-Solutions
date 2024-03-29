package com.leetcode.string.count_sort;

import java.util.Arrays;

public class _242_ValidAnagram {

    // time:O(nlogN) space:O(n)
    public static boolean isAnagram(String s, String t) {
        // solution1: sort
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        // sort
        Arrays.sort(str1);
        Arrays.sort(str2);

        for (int i = 0; i < s.length(); i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }

    // time:O(n) space:O(1)
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        // 利用count sort
        int[] counter = new int[256];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)]++;
            counter[t.charAt(i)]--;
        }

        // 应该是循环count里面的长度而不是s.length()
        for (int count : counter) {
            if (count != 0) return false;
        }
        return true;
    }
}
