package com.leetcode.math;

public class _50_PowXN {

    /**
     *  50. Pow(x, n)
     *  when: 2019/04/08
     *  Review1:2019/7/24
     * Test case:
        7^5 = 7^2 * 7^2 * 7 = (7^1 * 7^1) * (7^1 * 7^1) * 7 =
        (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * 7

         eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4

         eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8
     * @param x
     * @param n
     * @return
     */
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
        if (n == 0) return 1;
        
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
            if (i % 2 != 0) res *= x;
            x *= x;
        }
        return (n < 0) ? 1.0 / res : res;
    }

    public static void main(String[] args) {
        myPow(7, 5);
    }


}
