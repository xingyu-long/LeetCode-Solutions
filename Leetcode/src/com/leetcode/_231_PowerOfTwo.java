package com.leetcode;

public class _231_PowerOfTwo {

    /**
     * 231. Power of Two
     * When: 2019/04/11
     *
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
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
