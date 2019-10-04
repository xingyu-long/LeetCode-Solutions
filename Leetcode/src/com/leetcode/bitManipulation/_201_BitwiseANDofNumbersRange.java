package com.leetcode.bitManipulation;

public class _201_BitwiseANDofNumbersRange {

    /**
     * 201. Bitwise AND of Numbers Range
     * When: 2019/06/17
     * review1: 2019/9/28

        2 = 0010
        3 = 0011
        &
            0010

        0001
        0001

        0010

     思路：因为 0&1 = 0 1&1=1
     所以一直把那些不相同的移动掉，保留相同的（因为后面&运算之后也是0 然后保存其移动的距离，再返回来即可）
     * @param m
     * @param n
     * @return
     */
    // time:O(n) space:O(1)
    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            offset++;
        }
        return m << offset;
    }
}