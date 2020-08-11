package com.leetcode.graph.UnionFind;

public class _547_FriendCircles {
    public int findCircleNum(int[][] M) {
        // DFS / union find
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
        int m = M.length;
        int n = M[0].length;
        int res = 0;
        UF uf = new UF(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }


    public class UF {
        int[] id;
        int[] size;
        int count;

        public UF(int n) {
            id = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = n;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (size[rootP] > size[rootQ]) {
                size[rootP] += size[rootQ];
                id[rootQ] = rootP;
            } else {
                size[rootQ] += size[rootP];
                id[rootP] = rootQ;
            }
            count--;
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];// 优化操作。
                p = id[p];
            }
            return p;
        }
    }

    // time:O(n^2) space:O(n)
    public int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
        int m = M.length;
        int n = M[0].length;

        int res = 0;
        boolean[] visited = new boolean[m];
        // 验证每个人的朋友有哪些。dfs递归的是当前i行的朋友
        for (int i = 0; i < m; i++) {
            // 这里应该是m[i][i] 当前出发
            if (!visited[i] && M[i][i] == 1) {
                res++;
                dfs(M, i, n, visited);
            }
        }
        return res;
    }

    public void dfs(int[][] M, int i, int n, boolean[] visited) {
        if (visited[i]) return;
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && M[i][j] == 1) dfs(M, j, n, visited);
        }
    }
}
