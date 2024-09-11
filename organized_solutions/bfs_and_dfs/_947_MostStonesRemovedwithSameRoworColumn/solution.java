package com.leetcode.bfs_and_dfs;

import java.util.HashSet;

public class _947_MostStonesRemovedwithSameRoworColumn {
    // 也是连通图的题。可以用union find 和dfs。
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

        public int find(int p) {
            while (id[p] != p) {
                p = id[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pId = find(p);
            int qId = find(q);
            if (pId == qId) return;
            if (size[pId] > size[qId]) {
                id[qId] = pId;
                size[pId] += size[qId];
            } else {
                id[pId] = qId;
                size[qId] += size[pId];
            }
            count--;
        }
    }
    // time:O(n * n) space:O(n)
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        int n = stones.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] ||
                        stones[i][1] == stones[j][1]) {
                    // 直接使用其每个数组对应的index即可
                    uf.union(i, j); // 因为我们并不用二维坐标。
                }
            }
        }
        return n - uf.count;
    }

    // hashset里面放的int[]
    // time:O(n^2) 最差的情况，每个stone 都是独立的connected component。
    public int removeStones2(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        int n = stones.length;
        int count = 0;
        HashSet<int[]> visited = new HashSet<>();
        for (int [] stone : stones) {
            if (!visited.contains(stone)) {
                dfs(stone, stones, visited);
                count++;
            }
        }
        return n - count;
    }

    public void dfs(int[] currStone, int[][] stones, HashSet<int[]> visited) {
        visited.add(currStone);
        for (int[] stone : stones) {
            if (!visited.contains(stone)) {
                if (currStone[0] == stone[0] || currStone[1] == stone[1]) {
                    dfs(stone, stones, visited);
                }
            }
        }
    }
}
