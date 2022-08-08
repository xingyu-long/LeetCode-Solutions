package com.leetcode.geometry;

import java.util.HashSet;

/**
 * @Date: 06/24/2020
 * @Description: TODO
 **/
public class _391_PerfectRectangle {
    // time:O(n), space:O(n)
    // 因为不会overlap，所以最后只会有四个顶点保留，其他顶点遇到相同的时候会弹出。
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 ||
            rectangles[0] == null || rectangles[0].length == 0) {
            return false;
        }
        int area = 0;
        HashSet<String> points = new HashSet<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            area += (x2 - x1) * (y2 - y1);

            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            String pointOne = x1 + " - " + y1;
            String pointTwo = x1 + " - " + y2;
            String pointThree = x2 + " - " + y2;
            String pointFour = x2 + " - " + y1;
            if (!points.add(pointOne)) {
                points.remove(pointOne);
            }
            if (!points.add(pointTwo)) {
                points.remove(pointTwo);
            }
            if (!points.add(pointThree)) {
                points.remove(pointThree);
            }
            if (!points.add(pointFour)) {
                points.remove(pointFour);
            }
        }
        // 只有4个点可以出现奇数次
        if (points.size() != 4) {
            return false;
        }
        // 如果围成的最大的四个点不存在
        if (!points.contains(minX + " - " + minY) || !points.contains(minX + " - " + maxY) ||
            !points.contains(maxX + " - " + maxY) || !points.contains(maxX + " - " + minY)) {
            return false;
        }
        return area == (maxX - minX) * (maxY - minY);
    }
}
