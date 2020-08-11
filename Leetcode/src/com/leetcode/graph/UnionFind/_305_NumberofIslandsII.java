package com.leetcode.graph.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 07/23/2020
 * @Description: Union Find
 **/
public class _305_NumberofIslandsII {
    // 先将其id值为-1，表示没有访问过
    // 然后每次赋值，之后看四个方向是否有在一个里面的如果不是，则需要union
    class UF {

        int[] size;
        int[] id;

        public UF(int m, int n) {
            size = new int[m * n];
            id = new int[m * n];
            Arrays.fill(size, 1);
            Arrays.fill(id, -1);
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
        }

        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UF uf = new UF(m, n);
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int count = 0;
        for (int[] pos : positions) {
            int index = n * pos[0] + pos[1];
            // 有的test case会重复加入这一个pos
            if (uf.id[index] != -1) {
                res.add(count);
            } else {
                uf.id[index] = index;
                count++;
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    int nextIndex = x * n + y;
                    if (x < 0 || x >= m || y < 0 || y >= n || uf.id[nextIndex] == -1) continue;
                    int rootNext = uf.find(nextIndex);
                    // 这里需要注意
                    if (uf.find(index) != rootNext) {
                        // 需要union
                        uf.union(index, nextIndex);
                        count--;
                    }
                }
                res.add(count);
            }
        }
        return res;
    }
}
