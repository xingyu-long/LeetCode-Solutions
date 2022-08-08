package com.leetcode.math;

/**
 * @Date: 07/26/2020
 * @Description: Math, Iterate
 **/
public class _1523_CountOddNumbersinanIntervalRange {
    // time:O(n)
    // space:O(1)
    public int countOdds(int low, int high) {
        int count = 0;
        if (low % 2 == 0) low += 1;
        while (low <= high) {
            count++;
            low += 2;
        }
        return count;
    }


}
