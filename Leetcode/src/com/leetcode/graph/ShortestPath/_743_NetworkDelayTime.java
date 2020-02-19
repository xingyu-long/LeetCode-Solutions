package com.leetcode.graph.ShortestPath;

import java.util.*;

public class _743_NetworkDelayTime {
    // DFS
    // time:O(nlogn + n^2) space:O(N + E) 这个需要看看别人的解析。
    HashMap<Integer, Integer> dist;

    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
        }
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
        }
        dist = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dfs(graph, K, 0);
        int res = 0;
        for (int dis : dist.values()) {
            if (dis == Integer.MAX_VALUE) return -1;
            // 就表明这些点都可达，我们选距离最远的那个即可。
            res = Math.max(res, dis);
        }
        return res;
    }

    public void dfs(HashMap<Integer, List<int[]>> graph, int node, int time) {
        if (time >= dist.get(node)) return;
        dist.put(node, time);
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node))
                dfs(graph, info[1], time + info[0]);
        }
    }

    // 不是严格的最短路径，题目的意思是能够从一点走到其余的全部点，其中路径最小的。
    // Dijkstra's based on heap
    // heap 决定保持了最小的访问顺序
    // E : edges
    // time: O(ElogE)
    public int networkDelayTime2(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, K});
        Map<Integer, Integer> dist = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dis = curr[0];
            int node = curr[1];
            if (dist.containsKey(node)) continue;// 表示已经访问过了
            dist.put(node, dis);
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int adj = edge[0], d2 = edge[1];
                    if (!dist.containsKey(adj))
                        pq.offer(new int[]{dis + d2, adj});
                }
            }
        }
        if (dist.size() != N) return -1;
        int res = 0;
        for (int dis : dist.values()) {
            res = Math.max(res, dis);
        }
        return res;
    }

    // Bellman-Ford algorithm
    // time:O(V * E) space:O(V)
    public int networkDelayTime3(int[][] times, int N, int K) {
        double[] disTo = new double[N];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[K - 1] = 0;
        // 最多有i-1次，这个也需要记住。
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) { // 遍历所有的edges
                int u = edge[0] - 1;
                int v = edge[1] - 1;
                int w = edge[2];
                // relax ops
                if (disTo[u] + w < disTo[v]) {
                    disTo[v] = disTo[u] + w;
                }
            }
        }
        double res = Double.MIN_VALUE;
        for (double dis : disTo) {
            res = Math.max(dis, res);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
    }
}
