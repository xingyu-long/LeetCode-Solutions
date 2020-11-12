package com.leetcode.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Date: 06/14/2020
 * @Description: Graph, Path
 **/
public class _787_CheapestFlightsWithinKStops {

    // 也可以用DP的方法。
    // 不是shortest path的解法，只是每次计算你最早能够到达的距离以及dst对应distance
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = buildGraph(flights);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{src, 0, K + 1});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            int left = curr[2];
            if (node == dst) {
                return dist;
            }
            Map<Integer, Integer> map = graph.get(node);
            if (map == null) {
                continue;
            }
            if (left > 0) {
                for (int adj : map.keySet()) {
                    pq.offer(new int[]{adj, dist + map.get(adj), left - 1});
                }
            }
        }
        return -1;
    }


    private Map<Integer, Map<Integer, Integer>> buildGraph(int[][] flights) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new HashMap<>());
            map.get(flight[0]).put(flight[1], flight[2]);
        }
        return map;
    }
}
