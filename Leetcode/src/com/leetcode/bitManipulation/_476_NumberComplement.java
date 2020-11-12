package com.leetcode.bitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 05/04/2020
 * @Description: Bit Manipulation
 **/
public class _476_NumberComplement {

    // 比较直接的方法，利用空间做记录
    public int findComplement(int N) {
        if (N == 0) {
            return 1; // corner case
        }
        List<Integer> digits = new ArrayList<>();
        while (N != 0) {
            digits.add(N % 2 == 0 ? 1 : 0);
            N /= 2;
        }
        int res = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            res = res * 2 + digits.get(i);
        }
        return res;
    }

    // 利用1's(原来数据的个数) 和原来的数据做 ^，这样就相反了。
    public int findComplement2(int num) {
        int temp = num;
        int allOnes = 0;
        while (temp != 0) {
            allOnes <<= 1;
            allOnes += 1;
            temp >>= 1;
        }
        return allOnes ^ num;
    }

    // 32 bit: 00000000....1011 num
    // need:   00000000....0100 result
    //         11111111....0100 ~num
    //         00000000...10000 highestOne << 1
    //         00000000....1111 highestOne << 1 - 1
    //                      res ~num & (highestOne << 1 - 1)
    public int findComplement3(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        return num & mask;
    }

    // 32 bit: 00000000....1011 num
    //                     1111 x
    //                      res x - num
    // 这个和第二种方法一样的
    public int findComplement4(int num) {
        int x = 1;
        while (num > x) {
            x = x * 2 + 1;
        }
        return x ^ num;
    }

    // 看对应的位置情况 然后重新组合回去。
    public int findComplement5(int N) {
        if (N == 0) return 1;
        int res = 0;
        int index = 0;
        while (N != 0) {
            int digit = N % 2;
            int last = (digit == 1 ? 0 : 1);
            int add = last << index;
            res = res | add;
            N /= 2;
            index++;
        }
        return res;
    }
}
