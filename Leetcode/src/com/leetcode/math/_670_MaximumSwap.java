package com.leetcode.math;

/**
 * @Date: 05/19/2020
 * @Description: Number
 **/
public class _670_MaximumSwap {
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int[] indexes = new int[10];
        for (int i = 0; i < digits.length; i++) {
            indexes[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (indexes[k] > i) { // 当前k这个char存在并且其位置比现在大，所以可以换到前面来
                    char temp = digits[i];
                    digits[i] = digits[indexes[k]];
                    digits[indexes[k]] = temp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        return num;
    }
}
