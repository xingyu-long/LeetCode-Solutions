package com.leetcode.math;

/**
 * _1262_GreatestSumDivisiblebyThree
 */
public class _1262_GreatestSumDivisiblebyThree {

    public int maxSumDivThree(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        // 防止越界
        int first1Remain = Integer.MAX_VALUE / 2 - 1, second1Remain = Integer.MAX_VALUE / 2 - 1;
        int first2Remain = Integer.MAX_VALUE / 2 - 1, second2Remain = Integer.MAX_VALUE / 2 - 1;
        for (int num : nums) {
            sum += num;
            if (num % 3 == 1) {
                // 找到前面两个余数为1的数
                if (num < first1Remain) {
                    second1Remain = first1Remain;
                    first1Remain = num;
                } else if (num < second1Remain) {
                    second1Remain = num;
                }
            } else if (num % 3 == 2) {
                if (num < first2Remain) {
                    second2Remain = first2Remain;
                    first2Remain = num;
                } else if (num < second2Remain) {
                    second2Remain = num;
                }
            }
        }
        if (sum % 3 == 0)
            return sum;
        else if (sum % 3 == 1)
            return sum - Math.min(first1Remain, first2Remain + second2Remain);
        else
            return sum - Math.min(first2Remain, first1Remain + second1Remain);
    }
}
