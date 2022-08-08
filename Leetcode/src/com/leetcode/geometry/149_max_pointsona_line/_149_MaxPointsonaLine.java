/*
 * @Date: 12/30/2019 16:12:56
 * @LastEditTime: 07/26/2022 09:44:40
 * @Description: Math, Geometry
 */
package com.leetcode.geometry;

import java.util.HashMap;
import java.util.Map;

public class _149_MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        // time: (n^2)
        if (points == null || points.length == 0) {
            return 0;
        }
        int n = points.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int samePoints = 1; // 包括它自己
            int maxPoints = 0;
            int[] p1 = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] p2 = points[j];
                if (p1[0] == p2[0] && p1[1] == p2[1]) {
                    samePoints++;
                } else {
                    String slope = getSlope(p1, p2);
                    // System.out.println("p1 = " + Arrays.toString(p1) + " p2 = " + Arrays.toString(p2) + " slope = " + slope);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                    maxPoints = Math.max(maxPoints, map.get(slope));
                }
            }
            res = Math.max(res, maxPoints + samePoints);
        }
        return res;
    }
    
    public String getSlope(int[] p1, int[] p2) {
        int dy = p2[1] - p1[1];
        int dx = p2[0] - p1[0];
        // y坐标相同，那就利用y坐标的值来表示在同一条垂直线
        if (dy == 0) {
            return "0" + "-" + p1[1];
        }
        // x坐标相同，那就利用x坐标的值来表示在同一条水平线
        if (dx == 0) {
            return p1[0] + "-" + "0";
        }
        int d = gcd(dx, dy);
        return (dx / d) + "-" + (dy / d);
    }
    
    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
