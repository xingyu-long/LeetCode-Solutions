package com.leetcode.math;

public class _233_NumberofDigitOne {

    /**
     * 233. Number of Digit One
     * When:2019/8/10
     * Difficulty: Hard
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        int res = 0, a = 1, b = 1;
        while (n > 0) {
            res += (n + 8) / 10 * a;
            if (n % 10 == 1) res += b;
            b += n % 10 * a;
            a *= 10;
            n /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        countDigitOne(20);
    }
}
