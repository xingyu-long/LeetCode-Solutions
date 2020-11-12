package com.leetcode.array.interval;

import java.util.Arrays;

/**
 * @Date: 06/03/2020, 10/24/2020
 * @Description: Interval, Greedy
 **/
public class _435_NonoverlappingIntervals {

    // time:O(nlogn) space:O(1)
    // 尽量选择end比较小的保留
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null
            || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        int res = 0, n = intervals.length;
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] < end) {
                res++;
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return res;
    }
}
