package com.leetcode.math;

/**
 * @Date: 2019/04/08, 2019/7/24, 05/24/2020, 07/16/2020
 * @Description: divide and conquer
 **/
public class _50_PowXN {

    // time:O(logN) space:O(N)
    public static double myPow(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, n);
        }
    }

    // 利用对半分 recursion
    public static double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double half = pow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    // iterative
    public double myPow2(double x, int n) {
        double res = 1;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return (n < 0) ? 1.0 / res : res;
    }

    public static void main(String[] args) {
        myPow(7, 5);
    }
}
