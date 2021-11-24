/*
 * @Date: 08/31/2020 07:22:33
 * @LastEditTime: 11/22/2021 21:33:12
 * @Description: Union Find, HashMap.
 */
package com.leetcode.graph.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class _952_LargestComponentSizebyCommonFactor {
    public int largestComponentSize(int[] A) {
        // 利用uf
        int n = A.length;
        UnionFind uf = new UnionFind(n);
        Map<Integer, Integer> valToIndex = new HashMap<>();
        // 因子 -> index from A;
        // 利用factor来放一起
        // time: O(N * sqrt(value))
        for (int i = 0; i < n; i++) {
            int value = A[i];
            for (int j = 2; j * j <= value; j++) {
                if (value % j == 0) {
                    putOrUnion(j, i, valToIndex, uf);
                    putOrUnion(value / j, i, valToIndex, uf);
                }
            }
            putOrUnion(value, i, valToIndex, uf);
        }
        return uf.maxSize();
    }

    public void putOrUnion(int val, int i, Map<Integer, Integer> valToIndex, UnionFind uf) {
        // 表明对于这个factor，还没有组成的情况，所以先存进去。
        if (!valToIndex.containsKey(val)) {
            valToIndex.put(val, i);
        } else {
            uf.union(i, valToIndex.get(val));
        }
    }
}
