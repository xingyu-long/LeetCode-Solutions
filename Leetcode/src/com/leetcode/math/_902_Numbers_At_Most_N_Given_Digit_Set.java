package com.leetcode.math;

/*
 * @Date: 11/21/2020 11:26:49
 * @LastEditTime: 11/21/2020 11:36:11
 * @Description: Math, pow
 */

public class _902_Numbers_At_Most_N_Given_Digit_Set {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int res = 0;
        // 计算有一位数字到n-1位数字的情况
        for (int i = 1; i < len; i++) {
            res += (int) Math.pow(digits.length, i);
        }
        // 从高位开始遍历
        for (int i = 0; i < len; i++) {
            boolean prefix = false;
            for (String d : digits) {
                int num1 = Integer.parseInt(d);
                int num2 = s.charAt(i) - '0';
                if (num1 < num2) { // ??dXXXX
                    res += (int) Math.pow(digits.length, len - i - 1);
                } else if (num1 == num2) { // ??dXXX, check next digits
                    prefix = true;
                    break;
                }
            }
            if (!prefix) {
                return res;
            }
        }
        return res + 1;
    }
}