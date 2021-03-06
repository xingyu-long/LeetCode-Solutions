package com.leetcode.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 11/11/2020
 * @Description: Math, Square
 **/
public class _593_Valid_Square {
    // 因为需要是验证是否为正方形，只需要算每个点之间的距离，
    // 应该四边都是相同，两个对角线的值相同，所以有两种不同的值
    // 但也需要注意0的情况。
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(dist(p1, p2), dist(p1, p3), dist(p1, p4),
                dist(p2, p3), dist(p2, p4), dist(p3, p4)));
        return !set.contains(0) && set.size() == 2;
    }

    private int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
