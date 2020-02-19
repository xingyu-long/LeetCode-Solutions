package com.leetcode.bitManipulation;

public class _371_SumofTwoIntegers {

    /**
     * 371. Sum of Two Integers
     * When:2019/9/30
     * Difficulty: Easy
     * @param a
     * @param b
     * @return
     */
    // https://www.youtube.com/watch?v=qq64FrA2UXQ
    // carry 相同于进位的部分，如果这部分为0，就表明不需要进位，直接使用XOR操作
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int carry = a & b;
            a = a ^ b; // 相当于相加的操作
            b = carry << 1; // 进位的部分需要在前面一位这个位置
        }
        return a;
    }
}