package com.leetcode.bitManipulation;

public class _190_ReverseBits {
    /**
     * 190. Reverse Bits
     * When: 2019/06/17
     * <p>
     * solution: 利用1所在位置，一个往左移动 一个往右移动
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }

    public int reverseBits2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res ^= (n & 1); // 这里是相加最后一位的操作。
            n >>= 1;
        }
        return res;
    }
}
