package com.leetcode.math;

/**
 * @Date: 2019/8/12, 07/04/2020
 * @Description: Math
 **/
public class _263_UglyNumber {

    //time:O(n) space:O(1)
    public boolean isUgly(int num) {
        if (num == 1) {
            return true;
        }
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}
