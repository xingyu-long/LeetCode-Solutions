package com.leetcode.graph.mst;

import java.util.PriorityQueue;

import com.leetcode.graph.union_find.UnionFind;

/**
 * @Date: 10/05/2020
 * @Description: Minimal Spanning Tree (MST).
 **/
public class Kruskal {
    // time: O(ElogV)
    // space: O(V+E)
    public static int minCostWith(int[][] edges, int vertices) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        UnionFind uf = new UnionFind(vertices);
        for (int[] edge : edges) {
            pq.offer(edge);
        }
        int res = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], v = curr[1];
            double w = curr[2];
            int rootU = uf.root(u);
            int rootQ = uf.root(v);
            if (rootU == rootQ) continue;
            uf.union(u, v);
            res += w;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 1}, {0, 3, 3}, {0, 2, 6}, {2, 3, 2}, {1, 2, 4}, {1, 3, 5}};
        // U, V, W
        System.out.println(minCostWith(edges, 4));
    }
}
