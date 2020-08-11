package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

class _66_PlusOne {
    /**
     *  66. Plus one
     *  When:2019/4/3
     *  Review1: 2019/7/21

        solution:
        考虑三种情况：
            case 1: 1011 -> 1012
            case 2: 1099 -> 1100
            case 3: 9999 -> 10000 (这里需要新开辟空间，原有的长度+1即可)
     * @param digits
     * @return
     */
    // time:O(n) space:O(n)
    public int[] plusOne1(int[] digits) {
        if (digits == null || digits.length == 0) return digits;
        List<Integer> list = new ArrayList<>();
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry += digits[i];
            list.add(carry % 10);
            carry /= 10;
        }
        // 进位的操作
        if (carry != 0) {
            list.add(carry);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[list.size() - i - 1] = list.get(i);
        }
        return res;
    }

    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) return digits;
        List<Integer> list = new ArrayList<>();
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry += digits[i];
            digits[i] = carry % 10;
            carry /= 10;
            if (carry == 0) return digits; // 表示下一次计算carry是否为空。
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    // 跟上面的情况一样
    public int[] plusOne3(int[] digits) {
        /**
        * 考虑三种情况：
        * case 1: 1011 -> 1012
        * case 2: 1099 -> 1100
        * case 3: 9999 -> 10000 (这里需要新开辟空间，原有的长度+1即可)
        *
        */
        if (digits == null || digits.length == 0) return digits;

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
