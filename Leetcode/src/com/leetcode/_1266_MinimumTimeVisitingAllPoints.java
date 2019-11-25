package com.leetcode;

public class _1266_MinimumTimeVisitingAllPoints {
    // 发现这个题，只需要做两个点的差值，最大的那个就是需要走的路径，因为恒定距离的原因？
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        if (points == null || points.length == 0) return res;
        for (int i = 1; i < points.length; i++) {
            int[] cur = points[i];
            int[] prev = points[i - 1];
            res += Math.max(Math.abs(cur[0] - prev[0]), Math.abs(cur[1] - prev[1]));
        }
        return res;
    }
}
