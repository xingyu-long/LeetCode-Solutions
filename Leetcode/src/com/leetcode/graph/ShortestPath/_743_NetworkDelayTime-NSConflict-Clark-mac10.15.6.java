package com.leetcode.graph.ShortestPath;

import java.util.*;

/**
 * @Date: 04/29/2020
 * @Description: Shortest Path: Dijkstra, Bellman-Ford, Floyd–Warshall
 **/
public class _743_NetworkDelayTime {

    // Dijkstra's based on heap
    // heap 决定保持了最小的访问顺序
    // E : edges
    // time: pq: ElogV max: V
    public int networkDelayTime(int[][] times, int N, int K) {
        // test 有误导，其实一个点可以由上面多个点过来，所以要更新最小值。
        // dist[], pq pop出来操作 需要一个邻接表
        if (times == null || times.length == 0 ||
            times[0] == null || times[0].length == 0) {
            return 0;
        }
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // 因为这个点不存在，需要额外处理
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{K, 0});
        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int currDis = curr[1];
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            dist[node] = currDis;
            // 里面的变量需要注意！
            // explore the neighbors
            if (map.get(node) != null) {
                HashMap<Integer, Integer> neigh = map.get(node);
                for (int adj : neigh.keySet()) {
                    // 这里需要写成map.get(node).get(adj)
                    if (currDis + neigh.get(adj) < dist[adj]) {
                        pq.offer(new int[]{adj, currDis + neigh.get(adj)});
                    }
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int dis : dist) {
            if (dis == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dis);
        }
        return res;
    }

    // Bellman-Ford
    // time: O(N * E) space: O(N)
    // 因为中途有相加的过程，所以需要注意！
    public int networkDelayTime2(int[][] times, int N, int K) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = dist[K] = 0;
        // 这里就是1到N
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                // from u to v
                int u = edge[0], v = edge[1];
                long w = edge[2];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        long res = Integer.MIN_VALUE;
        for (long dis : dist) {
            if (dis == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dis);
        }
        return (int) res;
    }

    // Floyd–Warshall's Algorithm
    // time:O(V^3) space:O(V^2)
    public int networkDelayTime3(int[][] times, int N, int K) {
        long dist[][] = new long[N + 1][N + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // 这里写忘记了
        for (int i = 1; i <= N; i++) {
            dist[i][i] = 0;
        }

        for (int[] edge : times) {
            int u = edge[0], v = edge[1];
            int w = edge[2];
            dist[u][v] = w;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        long res = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (dist[K][i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dist[K][i]);
        }
        return (int) res;
    }
}
