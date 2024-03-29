/*
 * @Date: 07/19/2022 10:19:55
 * @LastEditTime: 07/19/2022 10:20:56
 * @Description: Geometry, Sliding Window
 */
package com.leetcode.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1610_MaximumNumberofVisiblePoints {
    // time: O(nlogn)
    // space: O(2 * n)
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int atOrigin = 0; // 与当前的点重合
        for (List<Integer> p : points) {
            int dx = p.get(0) - location.get(0);
            int dy = p.get(1) - location.get(1);
            if (dx == 0 && dy == 0) {
                atOrigin++;
                continue;
            }
            // convert it to angle and add it to the list
            angles.add(Math.atan2(dy, dx) * (180 / Math.PI));
        }
        Collections.sort(angles);
        List<Double> temp = new ArrayList<>(angles);
        for (double d : angles) {
            temp.add(d + 360); // handle edge case
        }
        int res = 0;
        int start = 0, end = 0;
        while (end < temp.size()) {
            while (temp.get(end) - temp.get(start) > angle) {
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res + atOrigin;
    }
}

