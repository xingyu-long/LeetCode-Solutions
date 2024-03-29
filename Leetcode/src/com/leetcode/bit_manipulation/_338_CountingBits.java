package com.leetcode.bit_manipulation;

/**
 * @Date: 05/28/2020, 07/18/2020, 10/20/2020
 * @Description: Bit
 **/
public class _338_CountingBits {
    // 利用偶数和奇数的规律
    // https://leetcode.com/problems/counting-bits/discuss/270693/Intermediate-processsolution-for-the-most-voted-solution-i.e.-no-simplificationtrick-hidden
    // 对于奇数：bit的最后一位肯定为1，所以可以获取res[num - 1]的数量情况然后再加上1，这样就可以统计数量。
    // 对于偶数：因为bit最后一位为0，所以只需要向右移动一位即可。
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & 1) == 0) { // even
                res[i] = res[i >> 1];
            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res;
    }
}