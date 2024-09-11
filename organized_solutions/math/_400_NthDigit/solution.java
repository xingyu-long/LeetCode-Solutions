package com.leetcode.math;

public class _400_NthDigit {

    /**
     * 400.Nth Digit
     * When:2019/8/10
     * Difficulty: Medium
     *
     * @param n
     * @return
     */
    public static int findNthDigit(int n) {
        int len = 1;
        long count = 9; // 防止溢出
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len; //当前区间偏移几个位置
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len) - '0';
    }

    public static void main(String[] args) {
        findNthDigit(13);
    }
}
