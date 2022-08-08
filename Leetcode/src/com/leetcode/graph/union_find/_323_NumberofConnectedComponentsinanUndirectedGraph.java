package com.leetcode.graph.union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class _323_NumberofConnectedComponentsinanUndirectedGraph {

    /**
     * 323. Number of Connected Components in an Undirected Graph
     * When:11/2/2019
     * Difficulty: Medium
     * @param n
     * @param edges
     * @return
     */
    public static int countComponents(int n, int[][] edges) {
        // build adjacent list
        List<List<Integer>> adjs = new ArrayList<>();
        for (int i = 0; i < n; i++) adjs.add(new ArrayList<>());
        for (int[] edge : edges) {
            adjs.get(edge[0]).add(edge[1]);
        }
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, i, adjs);
                res++;
            }
        }
        return res;
    }

    public static void dfs(boolean[] visited, int node, List<List<Integer>> adjs) {
        visited[node] = true;
        for (int adj : adjs.get(node)) {
            dfs(visited, adj, adjs);
        }
    }

    // 利用union find + set得到不同的root即可
    public static int countComponents2(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < uf.getId().length; i++) {
            set.add(uf.getId()[i]);
        }
        return set.size();
    }

    // 利用简化版的uf
    public static int countComponents3(int n, int[][] edges) {
        int res = n;
        int[] root = new int[n];
        Arrays.fill(root, -1);

        for (int[] edge : edges) {
            int x = find(root, edge[0]);
            int y = find(root, edge[1]);
            if (x != y) {
                res--;
                root[x] = y;
            }
        }
        return res;
    }

    public static int find(int[] root, int p) {
        while (root[p] != -1) {
            p = root[p];
        }
        return p;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents2(5, edges));
    }
}
