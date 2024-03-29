package com.leetcode.bfs_and_dfs;

import java.util.*;

public class _815_BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        // routes里的每个index代表不同bus
        // bus所到的地方就是不同的stop
        if (S == T) return 0;
        int n = routes.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> visited_stop = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{S, 0});
        visited_stop.add(S);

        // 表示这个车所有走的路径已经访问过了。
        boolean[] visited_bus = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                map.putIfAbsent(j, new HashSet<>());
                map.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int count = curr[1];
            if (pos == T) return count;
            for (int bus : map.get(pos)) {
                // 这样的话， 就算在同一个bus的不同stop计算了不同的count，遇到相同的bus编号也就直接跳过了
                if (visited_bus[bus]) continue;
                for (int stop : routes[bus]) {
                    if (!visited_stop.add(stop)) continue;
                    queue.offer(new int[]{stop, count + 1});
                }
                visited_bus[bus] = true;
            }
        }
        return -1;
    }

    // 或者用level traversal 记下全局的res，这样方便理解
}
