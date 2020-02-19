package com.leetcode.string;

public class _1309_DecryptStringfromAlphabettoIntegerMapping {

    // time:O(n) space:O(n)
    public String freqAlphabets(String s) {
        // check the pos of i + 2 equals # or not
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (index + 2 < s.length() && s.charAt(index + 2) == '#') {
                char ch = 'j';
                ch += (Integer.parseInt(s.substring(index, index + 2)) - 10);
                sb.append(String.valueOf(ch));
                index += 3;
            } else {
                char ch = 'a';
                ch += (Integer.parseInt(s.substring(index, index + 1)) - 1);
                sb.append(String.valueOf(ch));
                index++;
            }
        }
        return sb.toString();
    }
}
