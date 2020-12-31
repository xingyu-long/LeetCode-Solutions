/*
 * @Date: 06/03/2020 19:59:17
 * @LastEditTime: 12/30/2020 13:50:45
 * @Description: Interval, Greedy
 */
package com.leetcode.array.interval;

import java.util.Arrays;

public class _435_NonoverlappingIntervals {

    // time:O(nlogn) space:O(1)
    // 尽量选择end比较小的保留
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        // 还是优先用start排序，然后找end 最早结束的情况。
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
