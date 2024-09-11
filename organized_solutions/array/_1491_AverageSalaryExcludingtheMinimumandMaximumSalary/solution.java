package com.leetcode.array;

/**
 * @Date: 06/27/2020
 * @Description:
 **/
public class _1491_AverageSalaryExcludingtheMinimumandMaximumSalary {
    // time:O(n) space:O(1)
    public double average(int[] salary) {
        if (salary == null || salary.length == 0) {
            return 0.0;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        double sum = 0;
        for (int num : salary) {
            sum += num;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return (sum - min - max) / (salary.length - 2);
    }
}
