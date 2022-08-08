package com.leetcode.math;

public class _1360_NumberofDaysBetweenTwoDates {
    // when: 02/23/2020
    // time: O(n) space: O(1)
    public int daysBetweenDates(String date1, String date2) {
        // 闰年只需要在二月多加一天
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int num1 = calculate(date1, months);
        int num2 = calculate(date2, months);
        return num1 < num2 ? num2 - num1 : num1 - num2;
    }

    // 需要考虑平年和闰年
    // 忘记转换，忘记写初始化res
    public int calculate(String date, int[] months) {
        String[] data = date.split("-");
        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);
        int res = 0;
        // leap year
        // year和month 右边都不是闭区间！因为后面还要计算
        for (int i = 1971; i < year; i++) {
            if (isLeap(i)) res += 366;
            else res += 365;
        }
        for (int i = 1; i < month; i++) {
            if (isLeap(year) && i == 2) res += 1;
            res += months[i];
        }

        res += day;
        return res;
    }

    public boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
