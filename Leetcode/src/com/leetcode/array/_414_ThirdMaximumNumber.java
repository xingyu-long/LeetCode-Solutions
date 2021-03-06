package com.leetcode.array;

/**
 * @Date: 07/05/2020
 * @Description: TODO
 **/
public class _414_ThirdMaximumNumber {

    // time:O(n) space: O(1)
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                if (third < second) {
                    third = second;
                }
                second = first;
                first = num;
            } else if (num > second) {
                if (num != first) {
                    third = second;
                    second = num;
                }
            } else if (num > third) {
                if (num != second) {
                    third = num;
                }
            }
        }
        return third != Long.MIN_VALUE ? (int) third : (int) first;
    }
}
