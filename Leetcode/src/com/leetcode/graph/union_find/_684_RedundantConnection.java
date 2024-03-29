package com.leetcode.graph.union_find;

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
            if (pid == qid) {
                return false;
            }
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
                edges[0] == null || edges[0].length == 0) {
            return new int[] {};
        }
        UF uf = new UF(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[] {};
    }

    // DFS O(n^2) 每次最长走n个长度。有n个edge需要验证。
    public int[] findRedundantConnection2(int[][] edges) {
        if (edges == null || edges.length == 0 ||
                edges[0] == null || edges[0].length == 0) {
            return new int[] {};
        }
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            HashSet<Integer> visited = new HashSet<>();
            // 没有加入u,v这个edge的时候，是否能够到达，能够的话就是circle
            if (canReach(u, v, map, visited)) {
                return edge;
            }
            map.putIfAbsent(u, new HashSet<>());
            map.putIfAbsent(v, new HashSet<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        return new int[] {};
    }

    // 只在是否能从u到达v
    public boolean canReach(int u, int v, HashMap<Integer, HashSet<Integer>> map,
            HashSet<Integer> visited) {
        if (u == v) {
            return true;
        }
        visited.add(u);
        if (map.get(u) != null) {
            for (int next : map.get(u)) {
                if (visited.contains(next)) {
                    continue;
                }
                if (canReach(next, v, map, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
