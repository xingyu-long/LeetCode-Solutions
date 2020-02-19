package com.leetcode.bitManipulation;

public class _476_NumberComplement {
    // 利用1's(原来数据的个数) 和原来的数据做 ^，这样就相反了。
    public int findComplement(int num) {
        int temp = num;
        int allOnes = 0;
        while (temp != 0) {
            allOnes <<= 1;
            allOnes += 1;
            temp >>= 1;
        }
        return allOnes ^ num;
    }
}
