package com.leetcode.math;

/**
 * @Date: 2019/04/04, 2019/7/20, 05/08/2020
 * @Description: Parentheses
 **/
public class _69_SqrtX {

    // 记住考虑其中overflow的情况
    public int mySqrt(int x) {
        long left = 0;
        long right = x; // 这里不能设置为x / 2，因为x可能为1
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid >= x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right * right <= x) {
            return (int) right;
        } else {
            return (int) left;
        }
    }
}
