package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/4/3, 2019/7/21, 07/06/2020
 * @Description: Add one.
 **/
class _66_PlusOne {

    // solution: 考虑三种情况： case 1: 1011 -> 1012 case 2: 1099 -> 1100 case 3: 9999 -> 10000
    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry += digits[i];
            digits[i] = carry % 10;
            carry /= 10;
            if (carry == 0) {
                return digits; // 表示下一次计算carry是否为空。
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    // 跟上面的情况一样
    public int[] plusOne3(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        //进位的情况
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        // eg: 999 这种情况后面已经全部置为0了，所以需要补一位
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res; //默认后面为0，所以也不需要digits赋给res
    }
}
