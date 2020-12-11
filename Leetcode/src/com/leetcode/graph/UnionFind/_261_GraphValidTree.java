/*
 * @Date: 11/02/2019 16:44:46
 * @LastEditTime: 12/07/2020 17:40:34
 * @Description: Union Find
 */
package com.leetcode.graph.UnionFind;

public class _261_GraphValidTree {
    class UF {
        int[] id;
        int[] size;
        int count;

        public UF(int n) {
            size = new int[n];
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = n;
        }

        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return false;
            if (size[rootP] > size[rootQ]) {
                size[rootP] += size[rootQ];
                id[rootQ] = rootP;
            } else {
                size[rootQ] += size[rootP];
                id[rootP] = rootQ;
            }
            count--;
            return true;
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (!uf.union(u, v))
                return false;
        }
        return uf.count == 1;
    }
}
