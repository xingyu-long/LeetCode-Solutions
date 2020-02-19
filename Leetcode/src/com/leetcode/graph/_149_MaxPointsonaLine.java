package com.leetcode.graph;

import java.util.HashMap;

public class _149_MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int samePoint = 0;
            int sameX = 1;
            int p1 = i;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int p2 = j;
                    if (points[p1][0] == points[p2][0] &&
                            points[p1][1] == points[p2][1]) samePoint++;
                    if (points[p1][0] == points[p2][0]) {
                        sameX++;
                        continue;
                    }

                    int num = points[p2][1] - points[p1][1];
                    int den = points[p2][0] - points[p1][0];
                    int gcd = gcd(num, den);
                    String key = (num / gcd) + "/" + (den / gcd);
                    map.put(key, map.getOrDefault(key, 1) + 1);
                    res = Math.max(res, map.get(key) + samePoint);
                }
            }
            res = Math.max(res, sameX);
        }
        return res;
    }

    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
