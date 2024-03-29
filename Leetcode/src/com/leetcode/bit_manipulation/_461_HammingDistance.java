/*
 * @Date: 12/12/2020 21:03:26
 * @LastEditTime: 12/12/2020 21:03:48
 * @Description: Bit, XOR(^)
 */
package com.leetcode.bit_manipulation;

public class _461_HammingDistance {
    public int hammingDistance(int x, int y) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int lastX = x & 1;
            x >>= 1;
            int lastY = y & 1;
            y >>= 1;
            if ((lastX ^ lastY) == 1)
                res++;
        }
        return res;
    }
}
