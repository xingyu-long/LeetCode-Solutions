package com.leetcode.quickUnion;

public class quickUnion {

    private int[] id;
    private int[] size;

    public quickUnion(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int root(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]]; // 优化操作
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (size[pRoot] > size[qRoot]) {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }
    public int maxSize() {
        int res = 0;
        for (int num : size) {
            res = Math.max(res, num);
        }
        return res;
    }
}
