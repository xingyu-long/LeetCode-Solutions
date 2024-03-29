package com.leetcode.dynamic_programming;

import java.util.HashMap;

public class _1066_CampusBikeII {
    // 相当于利用backtracking让每个worker尝试不同的搭配。时间复杂度是 n!
    // 这个和那个stick 也是有点像
    int res;
    public int assignBikes(int[][] workers, int[][] bikes) {
        res = Integer.MAX_VALUE;
        int n = bikes.length;
        boolean[] used = new boolean[n];
        dfs(0, workers, bikes, 0, used);
        return res;
    }

    public void dfs(int workerIndex, int[][] workers, int[][] bikes, int sum, boolean[] used) {
        if (workerIndex == workers.length) {
            res = Math.min(res, sum);
            return;
        }
        if (sum > res) return;

        for (int i = 0; i < bikes.length; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(workerIndex + 1, workers, bikes, sum + distance(workers[workerIndex], bikes[i]), used);
                used[i] = false;
            }
        }
    }

    public int distance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    // 也可以使用压缩的dp算法。其实可以用二维dp表示？
}
