package com.leetcode.bitManipulation;

/**
 * @Date: 2019/06/17, 07/12/2020
 * @Description: Bit,
 **/
public class _190_ReverseBits {
    // 相当于数字反转一样，把末位取出来然后放到当前结果的里，之后每次移动 <<。
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int digit = n & 1;
            n >>= 1;
            res <<= 1;
            res |= digit;
        }
        return res;
    }
}
