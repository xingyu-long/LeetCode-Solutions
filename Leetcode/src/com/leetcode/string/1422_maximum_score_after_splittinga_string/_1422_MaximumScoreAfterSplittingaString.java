package com.leetcode.string;

/**
 * @Date: 04/29/2020
 * @Description: Count
 **/
public class _1422_MaximumScoreAfterSplittingaString {
    // res = Zero(left) + One(right)
    //     = Zero(left) + (totalOne - One(left))
    //     = max[Zero(left) - One(left)] + totalOne;
    // time:O(n) space:O(1)
    public int maxScore(String s) {
        // left 看0 右边看1的个数
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int zeros = 0, ones = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeros++;
            else ones++;
            if (i != n - 1) {
                max = Math.max(zeros - ones, max);
            }
        }
        return max + ones;
    }
}
