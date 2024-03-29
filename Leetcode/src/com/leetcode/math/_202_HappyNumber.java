package com.leetcode.math;

import java.util.HashSet;

public class _202_HappyNumber {
    // 如果是unhappy number最后会重复
    // 主要是看取出的位数
    // time:O(logN) space:O(logN)
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int squareSum, remain;
        while (set.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1) {
                return true;
            }
            n = squareSum;
        }
        return false;
    }
}
