package com.leetcode;

public class _50_PowXN {

    /**
     * 50. Pow(x, n)
     * when: 2019/04/08
     *
     * Test case:
     * 7^5 = 7^2 * 7^2 * 7 = (7^1 * 7^1) * (7^1 * 7^1) * 7 =
     * (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * (7^0 * 7^0 * 7) * 7
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, n);
        }
    }

    public static double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }

    public static double MyPow2(double x, int n) {
        if (n == 0) return 1;
        double res = 1;
        // int: -2^32 ~ 2^32 - 1 但是这里abs之后可能会溢出 所以使用long
        long abs = Math.abs((long) n);
        while (abs > 0) {
            if (abs % 2 != 0){
                res *= x;
            }
            x *= x;
            abs /= 2;
        }
        if (n < 0) {
            return 1.0 / res;
        }
        return res;
    }

    public static void main(String[] args) {
        myPow(7, 5);
    }


}
