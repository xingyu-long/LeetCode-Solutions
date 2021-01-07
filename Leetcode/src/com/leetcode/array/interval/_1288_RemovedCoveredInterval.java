package com.leetcode.array.interval;

import java.util.Arrays;

public class _1288_RemovedCoveredInterval {
    public int removeCoveredIntervals(int[][] intervals) {
        // 先按照start顺序排序，然后按照end逆序排。这样就可以每次从最长的边界开始遍历。
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));
        int last = -1;
        int removed = 0;
        for (int[] interval : intervals) {
            if (interval[1] <= last) {
                removed++;
            } else {
                last = interval[1];
            }
        }
        return intervals.length - removed;
    }
}
