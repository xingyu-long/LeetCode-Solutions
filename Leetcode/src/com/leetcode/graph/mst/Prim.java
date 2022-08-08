package com.leetcode.graph.mst;

import java.util.*;

import com.leetcode.graph.union_find.UnionFind;

public class Prim {
    // time: O(ElogV)
    // space: O(V+E)
    public static int minCostWith(int[][] edges, int vertices) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int w = edge[2];
            map.putIfAbsent(u, new HashMap<>());
            map.get(u).put(v, w);
            map.putIfAbsent(v, new HashMap<>());
            map.get(v).put(u, w);
        }

        int res = 0;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // W, V;
        pq.offer(new int[]{0, 0});
        for (int i = 0; i < vertices; i++) {
            while (true) {
                int[] curr = pq.poll();
                int w = curr[0], v = curr[1];
                if (visited.contains(v)) continue;
                res += w;
                visited.add(v);
                Map<Integer, Integer> nexts = map.get(v);
                if (nexts == null)  continue;
                for (int key : nexts.keySet()) {
                    int val = nexts.get(key);
                    if (visited.contains(key)) continue;
                    pq.offer(new int[]{val, key});
                }
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 1}, {0, 3, 3}, {0, 2, 6}, {2, 3, 2}, {1, 2, 4}, {1, 3, 5}};
        // U, V, W
        System.out.println(minCostWith(edges, 4));
    }
}
