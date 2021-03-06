package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @Date: 12/11/2020 11:00:56
 * @LastEditTime: 12/11/2020 11:02:21
 * @Description: Interval, Map
 */

public class _436_FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        // 因为start是distinct 所以可以放到map里
        int n = intervals.length;
        Map<Integer, Integer> startToIdx = new HashMap<>();
        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            int[] curr = intervals[i];
            starts[i] = curr[0];
            startToIdx.put(curr[0], i);
        }
        Arrays.sort(starts);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] curr = intervals[i];
            int j = 0;
            for (; j < n; j++) {
                // 一旦找到开头比之前的结尾大就结束。
                if (curr[1] <= starts[j])
                    break;
            }
            res[i] = (j < n ? startToIdx.get(starts[j]) : -1);
        }
        return res;
    }
}
