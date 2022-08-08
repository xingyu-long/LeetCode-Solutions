/*
 * @Date: 01/27/2021 11:11:11
 * @LastEditTime: 01/27/2021 11:11:50
 * @Description: Math, Binary
 */
package com.leetcode.math;

public class _1680_ConcatenationOfConsecutiveBinaryNumbers {
    // time: O(n * ops(to binary))
    // https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/discuss/961446/Detailed-Thought-Process-with-Steps-Example-or-Java-8-1-Liner
    public int concatenatedBinary(int n) {
        int MOD = (int) Math.pow(10, 9) + 7;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = (sum * (int) Math.pow(2, Integer.toBinaryString(i).length()) + i) % MOD;
        }
        return (int) sum;
    }
}
