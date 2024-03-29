package com.leetcode.graph.shortest_path;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Date: 06/14/2020
 * @Description: Graph, Path
 **/
public class _787_CheapestFlightsWithinKStops {

    // BFS -> TLE
    // time: ?
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            map.putIfAbsent(u, new HashMap<>());
            map.get(u).put(v, w);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        int res = Integer.MAX_VALUE;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int pos = curr[0], cost = curr[1];
                if (pos == dst) {
                    res = Math.min(res, cost);
                }
                Map<Integer, Integer> nexts = map.get(pos);
                if (nexts != null && nexts.size() > 0) {
                    for (int next : nexts.keySet()) {
                        int nextCost = cost + nexts.get(next);
                        if (nextCost > res) {
                            continue;
                        }
                        queue.offer(new int[]{next, nextCost});
                    }   
                }
            }
            if (depth++ > k) {
                break;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // time: O(k * edge)
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        // Bellman Ford
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        // do k + 1 times
        for (int i = 0; i < k + 1; i++) {
            int[] temp = Arrays.copyOf(cost, n);
            for (int[] flight : flights) {
                int curr = flight[0], next = flight[1], price = flight[2];
                if (cost[curr] == Integer.MAX_VALUE) {
                    continue;
                }
                temp[next] = Math.min(temp[next], cost[curr] + price);
            }
            cost = temp;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1: cost[dst];
    }

    // Dijkstra's algorithm -> TLE
    // 计算在有限的步数里面的情况
    // O(E+V)*O(LogV) which is O((E+V)*LogV) = O(ELogV)
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], price = flight[2];
            map.putIfAbsent(u, new HashMap<>());
            map.get(u).put(v, price);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{src, 0, k + 1});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int pos = curr[0], cost = curr[1], stops = curr[2];
            if (pos == dst) {
                return cost;
            }
            Map<Integer, Integer> nexts = map.get(pos);
            if (nexts != null && nexts.size() > 0) {
                if (stops > 0) {
                    for (int next : nexts.keySet()) {
                        pq.offer(new int[]{next, nexts.get(next) + cost, stops - 1});
                    }
                }
            }
        }
        return -1;
    }
}
