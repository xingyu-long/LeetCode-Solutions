package com.leetcode.graph.UnionFind;

import java.util.HashMap;
import java.util.HashSet;

public class _684_RedundantConnection {
    // 684. Redundant Connection
    // time:O(nlog*n) ~ O(n)
    public class UF {
        int[] id;
        int[] size;

        public UF(int n) {
            id = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        // 平摊O(1)
        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        public boolean union(int p, int q) {
            int pid = find(p);
            int qid = find(q);
            if (pid == qid) return false;
            if (size[pid] > size[qid]) {
                size[pid] += size[qid];
                id[qid] = pid;
            } else {
                size[qid] += size[pid];
                id[pid] = qid;
            }
            return true;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0 ||
                edges[0] == null || edges[0].length == 0) return new int[]{};
        UF uf = new UF(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[]{};
    }


    // DFS O(n^2) 每次最长走n个长度。有n个edge需要验证。
    public int[] findRedundantConnection2(int[][] edges) {
        if (edges == null || edges.length == 0 ||
                edges[0] == null || edges[0].length == 0) return new int[]{};
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            HashSet<Integer> visited = new HashSet<>();
            if (dfs(u, v, map, visited)) return edge;

            if (!map.containsKey(u)) map.put(u, new HashSet<>());
            if (!map.containsKey(v)) map.put(v, new HashSet<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        return new int[]{};
    }

    public boolean dfs(int source, int dest, HashMap<Integer, HashSet<Integer>> map, HashSet<Integer> visited) {
        if (source == dest) return true;
        if (!map.containsKey(source) || !map.containsKey(dest)) return false;
        visited.add(source);
        if (map.get(source) != null) {
            for (int next : map.get(source)) {
                if (visited.contains(next)) continue;
                if (dfs(next, dest, map, visited)) return true;
            }
        }
        return false;
    }
}
