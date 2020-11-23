/*
 * @Date: 11/18/2020 10:22:26
 * @LastEditTime: 11/18/2020 10:24:28
 * @Description: Interval (Remove)
 */
package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1272_Remove_Interval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] curr = intervals[i];
            if (toBeRemoved[0] >= curr[1] || toBeRemoved[1] <= curr[0]) {
                // no intersect;
                res.add(Arrays.asList(curr[0], curr[1]));
            } else {
                // 前半部分（left，removed的左边）
                if (toBeRemoved[0] > curr[0]) {
                    res.add(Arrays.asList(curr[0], toBeRemoved[0]));
                }
                // 后半部分（removed的右边，right）
                if (toBeRemoved[1] < curr[1]) {
                    res.add(Arrays.asList(toBeRemoved[1], curr[1]));
                }
            }
        }
        return res;
    }
}
