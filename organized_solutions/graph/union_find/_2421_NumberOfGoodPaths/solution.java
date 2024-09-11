package com.leetcode.graph.union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _2421_NumberOfGoodPaths {
    class Solution {
        
        // https://leetcode.com/problems/number-of-good-paths/discuss/2621772/C%2B%2B-or-Java-or-Diagram-or-Union-Find
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            int n = vals.length;
            int res = 0;
             // build the graph: connect it only if the adj is less or equals than current node
            List<Integer>[] graph = new ArrayList[n];
    
            // value -> {node}
            Map<Integer, List<Integer>> tm = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                tm.putIfAbsent(vals[i], new ArrayList<>());
                tm.get(vals[i]).add(i);
                graph[i] = new ArrayList<>();            
            }
            
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                if (vals[u] >= vals[v]) {
                    graph[u].add(v);
                } else {
                    graph[v].add(u);
                }
            }
            
            UnionFind uf = new UnionFind(n);
            // go through values with sorted order
            for (int value : tm.keySet()) {
                for (int u : tm.get(value)) {
                    for (int v : graph[u]) {
                        // connect all neighbors
                        uf.union(u, v);
                    }
                }
                
                
                Map<Integer, Integer> group = new HashMap<>();
                for (int u : tm.get(value)) {
                    int root = uf.root(u);
                    group.put(root, group.getOrDefault(root, 0) + 1);
                }
                res += tm.get(value).size();
                for (int key : group.keySet()) {
                    int count = group.get(key);
                    res += count * (count - 1) / 2;
                }
            }
            return res;
        }
    }
}
