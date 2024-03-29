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

    public int removeCoveredIntervals2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int removed = 0, start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                if (intervals[i][0] >= start && intervals[i][1] <= end) {
                    removed++;
                } else {
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
            } else {
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        return intervals.length - removed;
    }
}
