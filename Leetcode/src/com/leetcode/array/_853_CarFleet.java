package com.leetcode.array;

import java.util.Arrays;

public class _853_CarFleet {
    // 小于一个时间 都可以成为一个fleet，因为也不能超过
    // time:O(nlogn) space:O(n * 2)
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length, res = 0;
        double[][] cars = new double[n][2];

        for (int i = 0; i < n; i++) {
            cars[i] = new double[]{position[i], (double)(target - position[i]) / speed[i]};
        }
        // 按照当前的坐标来排序
        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
        double cur = 0;
        // 从距离最近的开始
        for (int i = n - 1; i >= 0; i--) {
            if (cars[i][1] > cur) {
                cur = cars[i][1];
                res++;
            }
        }
        return res;
    }
}
