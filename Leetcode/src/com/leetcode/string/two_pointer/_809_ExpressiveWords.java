package com.leetcode.string.two_pointer;

public class _809_ExpressiveWords {
    // 809. Expressive Words
    // two pointer
    // time: O(len * (m + n)) 其中len为words.size(), m为S.size(), n为word中字符串的平均长度
    public int expressiveWords(String S, String[] words) {
        // 利用counting sort来做
        int res = 0;
        for (String word : words) {
            if (isValid(S, word)) res++;
        }
        return res;
    }


    public boolean isValid(String s, String word) {
        int i = 0, j = 0; // i for s, j for word;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i++) != word.charAt(j++)) return false;
            int c1 = 1, c2 = 1;
            while (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                i++;
                c1++;
            }
            while (j < word.length() && word.charAt(j) == word.charAt(j - 1)) {
                j++;
                c2++;
            }
            if (c1 == c2 || (c1 >= 3 && c1 > c2)) continue;
            return false;
        }
        return i == s.length() && j == word.length();
    }
}
