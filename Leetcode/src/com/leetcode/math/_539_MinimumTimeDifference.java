/*
 * @Date: 07/18/2022 22:30:44
 * @LastEditTime: 07/18/2022 22:31:50
 * @Description: Math
 */
package com.leetcode.math;

import java.util.Arrays;
import java.util.List;

public class _539_MinimumTimeDifference {
    // time: O(nlogn)
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            return 0;
        }
        int size = timePoints.size();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = convertToMin(timePoints.get(i));
        }
        Arrays.sort(data);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            int diff = data[i] - data[i - 1];
            int another = 24 * 60 - diff;
            res = Math.min(res, Math.min(diff, another));
        }
        int lastDiff = data[size - 1] - data[0];
        res = Math.min(res, lastDiff);
        res = Math.min(res, 24 * 60 - lastDiff);
        return res;
    }

    public int convertToMin(String s) {
        String[] strs = s.split(":");
        int hour = Integer.parseInt(strs[0]);
        int min = Integer.parseInt(strs[1]);
        return hour * 60 + min;
    }
}
