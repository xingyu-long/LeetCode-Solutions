package com.leetcode.bfsANDdfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Date: 05/13/2020
 * @Description: DFS
 **/
public class _1443_MinimumTimetoCollectAllApplesinaTree {
    // time:O(n) space:O(n)
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // 一开始直接想错了，想成了用BFS然后用level来计算
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < hasApple.size(); i++) {
            if (hasApple.get(i)) {
                set.add(i);
            }
        }
        if (set.size() == 0) {
            return 0;
        }
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            map.putIfAbsent(u, new HashSet<>());
            map.putIfAbsent(v, new HashSet<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        return dfs(0, set, visited, map);
    }

    private int dfs(int curr, HashSet<Integer> hasApple, boolean[] visited,
        Map<Integer, HashSet<Integer>> map) {
        visited[curr] = true;
        int total = 0;
        if (map.get(curr) == null) {
            return total;
        }
        for (int child : map.get(curr)) {
            if (visited[child]) {
                continue;
            }
            int cost = dfs(child, hasApple, visited, map);
            if (cost > 0 || hasApple.contains(child)) {
                total += 2 + cost;
            }
        }
        return total;
    }
}
