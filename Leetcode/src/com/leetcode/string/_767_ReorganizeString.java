package com.leetcode.string;

public class _767_ReorganizeString {

    // time:O(n) space:O(n + 26)
    public String reorganizeString(String S) {
        int[] counter = new int[26];
        for (int i = 0; i < S.length(); i++) {
            counter[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > max) {
                max = counter[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) return "";
        char[] res = new char[S.length()];
        int index = 0;
        // 先赋值最多的letter，
        while (counter[letter] > 0) {
            res[index] = (char) (letter + 'a');
            index += 2;
            counter[letter]--;
        }

        for (int i = 0; i < counter.length; i++) {
            while (counter[i] > 0) {
                if (index >= res.length) {
                    index = 1;// 从头开始。
                }
                res[index] = (char) (i + 'a');
                index += 2;
                counter[i]--;
            }
        }
        return new String(res);
    }
}
