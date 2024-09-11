/*
 * @Date: 01/22/2021 10:16:02
 * @LastEditTime: 01/22/2021 10:16:59
 * @Description: You need to fill out
 */
package com.leetcode.string;

import java.util.Arrays;

public class _1657_DetermineIfTwoStringsAreClose {
    // 可以交换的话，那出现的频率是相同的，并且那个位置也是相同的。
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] c1 = new int[26], c2 = new int[26];
        int[] p1 = new int[26], p2 = new int[26];
        for (char ch : word1.toCharArray()) {
            c1[ch - 'a']++;
            p1[ch - 'a'] = 1;
        }
        for (char ch : word2.toCharArray()) {
            c2[ch - 'a']++;
            p2[ch - 'a'] = 1;
        }
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2) && Arrays.equals(p1, p2);
    }
}
