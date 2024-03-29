package com.leetcode.bit_manipulation;

/**
 * @Date: 07/17/2020
 * @Description: Mask
 **/
public class _318_MaximumProductofWordLengths {

    // 利用重新计算的每个word对应的值  如果是不同的字符 & 结果会为0
    public int maxProduct(String[] words) {
        int n = words.length;
        if (words == null || n == 0) {
            return 0;
        }
        int[] mask = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (char ch : words[i].toCharArray()) {
                mask[i] |= 1 << (ch - 'a');
            }
            for (int j = 0; j < i; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    // 表明没有重复的字符
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
