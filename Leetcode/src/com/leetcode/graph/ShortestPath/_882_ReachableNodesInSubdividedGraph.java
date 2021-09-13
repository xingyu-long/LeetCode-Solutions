/*
 * @Date: 09/12/2021 17:37:09
 * @LastEditTime: 09/12/2021 17:39:22
 * @Description: Djistra's Algo
 */
package com.leetcode.graph.ShortestPath;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;


public class _882_ReachableNodesInSubdividedGraph {
    // 需要再看看
    // https://zxi.mytechroad.com/blog/graph/leetcode-882-reachable-nodes-in-subdivided-graph/
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        // build graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int w = edge[2];
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(u).put(v, w);
            graph.get(v).put(u, w);
        }
        // hp, node
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        Map<Integer, Integer> HP = new HashMap<>();
        pq.offer(new int[] { maxMoves, 0 });
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int hp = curr[0], node = curr[1];
            // already visited this node.
            if (HP.containsKey(node))
                continue;
            HP.put(node, hp);
            Map<Integer, Integer> e = graph.get(node);
            if (e == null)
                continue;
            for (Integer next : e.keySet()) {
                int nextHP = hp - (e.get(next) + 1);
                if (HP.containsKey(next) || nextHP < 0)
                    continue;
                pq.offer(new int[] { nextHP, next });
            }
        }
        int res = HP.size();
        for (int[] edge : edges) {
            int u = HP.getOrDefault(edge[0], 0);
            int v = HP.getOrDefault(edge[1], 0);
            res += Math.min(edge[2], u + v);
        }
        return res;
    }
}
