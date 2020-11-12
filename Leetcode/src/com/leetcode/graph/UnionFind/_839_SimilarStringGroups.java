package com.leetcode.graph.UnionFind;

/**
 * @Date: 05/30/2020, 10/09/2020
 * @Description: Union Find.
 **/
public class _839_SimilarStringGroups {

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
            while (id[p] != p) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp == rootq) {
                return;
            }

            if (size[rootp] > size[rootq]) {
                size[rootp] += size[rootq];
                id[rootq] = rootp;
            } else {
                size[rootq] += size[rootp];
                id[rootp] = rootq;
            }
            count--;
        }

        public int size() {
            return this.count;
        }
    }

    public int numSimilarGroups(String[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(A[i], A[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.size();
    }

    private boolean isSimilar(String s, String t) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
