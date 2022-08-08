package com.leetcode.graph.union_find;

public class _765_Couples_Holding_Hands {
    class UF {
        int[] size;
        int[] id;
        int count;

        public UF(int n) {
            size = new int[n];
            id = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                id[i] = i;
            }
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
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
    }

    // https://leetcode.com/problems/couples-holding-hands/discuss/117520/Java-union-find-easy-to-understand-5-ms
    // 第一个评论里面的数学证明
    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            // 访问完每一对。
            int a = row[2 * i];
            int b = row[2 * i + 1];
            // x / 2 当做每个group的id
            uf.union(a / 2, b / 2);
        }
        return n - uf.count;
    }
}
