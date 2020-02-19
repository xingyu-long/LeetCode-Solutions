package com.leetcode.graph;

import java.util.HashSet;

public class _356_LineReflection {
    /*
    1. Find the smallest and largest x-value for all points.
    2. If there is a line then it should be at y = (minX + maxX) / 2.
    3. For each point, make sure that it has a reflected point in the opposite side.
     */
    public boolean isReflected(int[][] points) {
        HashSet<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] pair : points) {
            set.add(pair[0] + "," + pair[1]);
            min = Math.min(min, pair[0]);
            max = Math.max(max, pair[0]);
        }
        int sum = min + max;
        for (int[] pair : points) {
            if (!set.contains(sum - pair[0] + "," + pair[1])) {
                return false;
            }
        }
        return true;
    }
}