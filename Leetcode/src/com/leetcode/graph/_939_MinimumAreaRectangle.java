package com.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Date: 04/25/2020
 * @Description: 向回看DP
 **/
public class _939_MinimumAreaRectangle {

    // 其实是找对角线的点是否存在，然后碰见同行同列的就跳过，然后再找当前的x能否到对角线点的y。（说明对角线关系成立并且构成了矩形。
    // time:O(n ^ 2) space:O(n)
    public int minAreaRect(int[][] points) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) &&
                    map.get(p2[0]).contains(p1[1])) {
                    res = Math.min(res, Math.abs(p1[0] - p2[0]) *
                        Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
