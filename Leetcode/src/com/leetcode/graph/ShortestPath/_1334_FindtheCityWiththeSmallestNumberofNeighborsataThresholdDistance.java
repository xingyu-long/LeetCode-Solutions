package com.leetcode.graph.ShortestPath;

import java.util.*;

// 好总结：https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/tutorial/
public class _1334_FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    // DFS解法，但是这个会错误，因为可能有的找到最短路径，这样就会有更多点可以到达。
    public class Data {
        HashMap<Integer, HashSet<Integer>> costAndNeighs;

        public Data() {
            costAndNeighs = new HashMap<>();
        }
    }
    // graph
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        HashMap<Integer, Data> adjs = new HashMap<>();
        for (int[] edge : edges) {
            adjs.putIfAbsent(edge[0], new Data());
            adjs.putIfAbsent(edge[1], new Data());

            adjs.get(edge[0]).costAndNeighs.putIfAbsent(edge[2], new HashSet<>());
            adjs.get(edge[1]).costAndNeighs.putIfAbsent(edge[2], new HashSet<>());

            adjs.get(edge[0]).costAndNeighs.get(edge[2]).add(edge[1]);
            adjs.get(edge[1]).costAndNeighs.get(edge[2]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            HashSet<Integer> visited = new HashSet<>();
            dfs(i, i, adjs, distanceThreshold, visited, map);
        }

        int min = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            min = Math.min(min, map.get(key).size());
        }
        List<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key).size() == min) {
                res.add(key);
            }
        }
        if (res.size() == 0) return 0;
        Collections.sort(res, Collections.reverseOrder());
        return res.get(0);
    }

    public void dfs(int node, int curr,  HashMap<Integer, Data> adjs, int dis, HashSet<Integer> visited,  HashMap<Integer, HashSet<Integer>> map) {
        if (dis < 0) return;
        if (visited.contains(curr)) return;
        visited.add(curr);
        if (node != curr) map.get(node).add(curr);
        if (adjs.get(curr) != null) {
            for (int cost : adjs.get(curr).costAndNeighs.keySet()) {
                for (int adj : adjs.get(curr).costAndNeighs.get(cost)) {
                    if (!visited.contains(adj)) {
                        dfs(node, adj, adjs, dis - cost, visited, map);
                    }
                }
            }
        }
    }

    // https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/discuss/490283/Java-PriorityQueue-%2B-BFS
    // 利用Djistra算法
    // Time Complexity: Dijkstra on one vertex is O(ElogV), so for all vertices is O(VElogV)
    // The description says 1 <= edges.length <= n * (n - 1) / 2, so O(E) = O(V^2)
    // Therefore, the final time complexity is O(V^3logV) which should be slower than Floyd Warshall (O(V^3))
    public int findTheCity2(int n, int[][] edges, int distanceThreshold) {
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
            // why larger distance polls first? 表示剩下可以用的路径越多，尽可能探索多的点，
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
    public int findTheCity3(int n, int[][] edges, int distanceThreshold) {
        long[][] d = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            d[i][i] = 0;// itself.
        }
        for (int[] edge : edges) {
            d[edge[0]][edge[1]] = Math.min(d[edge[0]][edge[1]], edge[2]);
            d[edge[1]][edge[0]] = Math.min(d[edge[1]][edge[0]], edge[2]);
        }
        // i to j through k
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (d[i][k] + d[k][j] < d[i][j]) { // 用long防止这里溢出
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && d[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= min) {
                min = count;
                res = i;
            }
        }
        return res;
    }
}
