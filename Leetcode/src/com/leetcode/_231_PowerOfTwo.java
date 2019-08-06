package com.leetcode;

public class _231_PowerOfTwo {

    /**
     * 231. Power of Two
     * When: 2019/04/11
     * review:2019/8/6
     * 2 : 10
     * 4 : 100
     * 8 : 1000
     * 16 : 10000
     *
     * n = 16 : 10000
     * n-1=15 : 01111
     * 利用 “位运算”
     * 所以只需要 求并  以及求与（0 & 0 = 0; 0 & 1 = 0; 1 & 1 = 1）
     * @param n
     * @return
     */
    // https://leetcode.com/problems/power-of-two/discuss/63966/4-different-ways-to-solve-Iterative-Recursive-Bit-operation-Math

    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    // 可以整除2的情况一直除以2
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}
