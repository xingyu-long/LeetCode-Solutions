package com.leetcode.bitManipulation;

public class _318_MaximumProductofWordLengths {

    /**
     * 318. Maximum Product of Word Lengths
     * When:2019/9/28
     * Difficulty:Hard
     * @param words
     * @return
     */
    // 利用重新计算的每个word对应的值  如果是不同的字符 & 结果会为0
    public int maxProduct(String[] words) {
        if (words.length == 0) return 0;
        int res = 0;
        int[] bytes = new int[words.length];
        // calculate bytes for each word;
        for (int i = 0; i < words.length; i++) {
            int val = 0;
            for (int j = 0; j < words[i].length(); j++) {
                val |= 1 << (words[i].charAt(j) - 'a');
            }
            bytes[i] = val;
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bytes[i] & bytes[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
