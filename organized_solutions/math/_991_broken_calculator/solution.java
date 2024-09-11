package com.leetcode.math;

/**
 * @Date: 08/12/2020
 * @Description: Math.
 **/
public class _991_BrokenCalculator {
    // O(log(Y/X))
    public int brokenCalc(int X, int Y) {
        // convert y to x;
        int res = 0;
        while (Y > X) {
            if (Y % 2 == 0) {
                Y /= 2;
            } else {
                Y += 1;
            }
            res++;
        }
        return res + X - Y;
    }
}