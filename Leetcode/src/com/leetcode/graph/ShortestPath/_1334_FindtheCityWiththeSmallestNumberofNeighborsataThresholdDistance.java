/*
 * @Date: 2020-01-26 10:48:30
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-18 15:12:13
 */
package com.leetcode.graph.ShortestPath;

import java.util.*;

// 好总结：https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/tutorial/
// http://www-inst.eecs.berkeley.edu/~cs61bl/r//cur/graphs/dijkstra-algorithm-runtime.html?topic=lab24.topic&step=4&course=
public class _1334_FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    // https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/discuss/490283/Java-PriorityQueue-%2B-BFS
    // 利用Djistra算法
    // Time Complexity: Dijkstra on one vertex is O(ElogV), so for all vertices is O(VElogV)
    // The description says 1 <= edges.length <= n * (n - 1) / 2, so O(E) = O(V^2)
    // Therefore, the final time complexity is O(V^3logV) which should be slower than Floyd Warshall (O(V^3))
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 建立邻接表关系
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashMap<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).put(edge[1], edge[2]);
            map.get(edge[1]).put(edge[0], edge[2]);
        }

        int min = n + 1;
        int res = -1;
        // 针对于每个点进行搜索，最好找出最多点，这样结果才对
        for (int i = 0; i < n; i++) {
            // 是把剩余的距离越大先poll出来。初始化把每个点还剩total的距离。表示剩下可以用的路径越多，尽可能探索多的点，
            // 这也是为啥DFS不可以的原因，因为有的路其实可以达到，但是没有去到过
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
            pq.offer(new int[]{i, distanceThreshold});
            HashSet<Integer> visited = new HashSet<>();
            int count = 0;
            while (!pq.isEmpty()) {
                int[] city = pq.poll();
                if (!visited.contains(city[0])) {
                    visited.add(city[0]);
                    count++;
                } else continue;
                HashMap<Integer, Integer> adjAndCost = map.get(city[0]);
                for (int adj : adjAndCost.keySet()) {
                    if (!visited.contains(adj) && city[1] >= adjAndCost.get(adj)) {
                        pq.offer(new int[]{adj, city[1] - adjAndCost.get(adj)});
                    }
                }
            }
            // 这里减一是因为之前一开始就是没有mark visited，所以count会多加一
            if (count - 1 <= min) {
                min = count - 1;
                res = i;
            }
        }
        return res;
    }


    // Floyd–Warshall's Algorithm
    // time:O(V^3)
    public int findTheCity2(int n, int[][] edges, int distanceThreshold) {
        // V^3
        long[][] dist = new long[n][n];
        for (long[] row : dist) 
            Arrays.fill(row, Integer.MAX_VALUE);
        
        for (int i = 0; i < n; i++) 
            dist[i][i] = 0;
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int w = edge[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int res = n;
        int index = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) count++;
            }
            if (count <= res) {
                res = count;
                index = i;
            }
        }
        return index;
    }
}
