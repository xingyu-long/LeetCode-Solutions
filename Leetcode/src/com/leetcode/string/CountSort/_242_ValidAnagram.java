package com.leetcode.string.CountSort;

import java.util.*;

public class _242_ValidAnagram {

    /**
     * 242. Valid Anagram
     * When: 2019/03/28
     * Review1:2019/8/7
     * solution：
     * 1. 先转换成字符数组然后sort 比较即可
     * 2. 利用counting sort
     *
     *
     * Follow up
     * Q: What if the inputs contain unicode characters? How would you adapt your solution to such case?
     *
     * A: Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the entire range of unicode characters,
     * @param s
     * @param t
     * @return
     */
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

    public static void main(String[] args) {
        System.out.println(isAnagram("aacc", "ccac"));
    }
}
