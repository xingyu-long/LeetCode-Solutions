package com.leetcode.string;

public class _1370_IncreasingDecreasingString {
    /**
     * When: 03/14/2020
     * @param s
     * @return
     */
    // time: O(n)
    public String sortString(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            add(sb, count, true);
            add(sb, count, false);
        }
        return sb.toString();
    }

    public void add(StringBuilder sb, int[] count, boolean asc) {
        for (int i = 0; i < 26; i++) {
            int j = asc ? i : 25 - i;
            if (count[j]-- > 0) sb.append((char)(j + 'a'));
        }
    }
    
}