package com.leetcode.array.interval;

import java.util.Map;
import java.util.TreeMap;

public class _1094_CarPooling {
    // 直接计算出每个时刻，剩余的capacity。
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0 || trips[0] == null || trips[0].length == 0) {
            return true;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        for (int value : map.values()) {
            capacity -= value;
            if (capacity < 0) return false;
        }
        return true;
    }

    // 当然因为输入确定，我们也可以创建一个固定的数组，计算每个时间的capacity！
    // https://leetcode.com/problems/car-pooling/discuss/317611/C%2B%2BJava-O(n)-Thousand-and-One-Stops
}
