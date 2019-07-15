package com.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class _452_MinimumNumberofArrowstoBurstBalloons {

    /**
     *  452. Minimum Number of Arrows to Burst Balloons
     *  When:2019/7/15
     *  Difficulty: Medium
     *  Solution:
     *  greedy
     *  贪心策略：一个弓箭手尽可能打到多的气球
     *  这里都是取左右区间最小的情况，然后当后面的start大于现在的end 说明这个需要另外一个弓箭手
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int res = 1;
        int end = points[0][1];//保留第一个点
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                res++;
                end = points[i][1];
            } else {
                end = Math.min(end, points[i][1]);
            }
        }
        return res;
    }
}