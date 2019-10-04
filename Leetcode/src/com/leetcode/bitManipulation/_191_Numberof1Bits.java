package com.leetcode.bitManipulation;

public class _191_Numberof1Bits {
    /**
     * 191. Number of 1 Bits
     * When:2019/9/30
     * Difficulty: Easy
     * @param n
     * @return
     */
    // 利用 n & 1计算末尾是否为0，如果为1则就是算入1，否则为0
    // time:O(len(n)) space:O(1)
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n >>= 1;
        }
        return res;
    }
}
