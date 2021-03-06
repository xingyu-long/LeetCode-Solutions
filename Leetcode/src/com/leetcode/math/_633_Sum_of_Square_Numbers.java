package com.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 10/15/2020
 * @Description: math, two pointer
 **/
public class _633_Sum_of_Square_Numbers {
    // one pass 但是需要额外的空间去存。
    // Time: Sqrt(C)
    public boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i)) return true;
        }
        return false;
    }

    // two pointer
    // Time: Sqrt(C)
    public boolean judgeSquareSum2(int c) {
        int a = 0, b = (int) Math.sqrt(c);
        while (a <= b) {
            int sum = a * a + b * b;
            if  (sum == c) return true;
            else if (sum > c) b--;
            else a++;
        }
        return false;
    }
}
