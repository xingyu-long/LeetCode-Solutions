package com.leetcode.graph.union_find;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _547_NumberofProvinces {

    // time:O(n^2) space:O(n)
    // 这个更加的巧妙。也可以用union find
    public int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) {
            return 0;
        }
        int m = M.length;
        int n = M[0].length;

        int res = 0;
        boolean[] visited = new boolean[m];
        // 验证每个人的朋友有哪些。dfs递归的是当前i行的朋友
        for (int i = 0; i < m; i++) {
            // 这里应该是m[i][i] 当前出发
            if (!visited[i] && M[i][i] == 1) {
                res++;
                merge(M, i, n, visited);
            }
        }
        return res;
    }

    public void merge(int[][] M, int i, int n, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && M[i][j] == 1) {
                merge(M, j, n, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (isConnected[i][j] == 1) {
                    map.putIfAbsent(i, new HashSet<>());
                    map.get(i).add(j);
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connect(map, i, visited);
                res++;
            }
        }
        return res;
    }
    
    public void connect(Map<Integer, Set<Integer>> map, int start, boolean[] visited) {
        if (visited[start]) return;
        visited[start] = true;
        Set<Integer> nexts = map.get(start);
        if (nexts != null && nexts.size() > 0) {
            for (int next : nexts) {
                if (visited[next]) continue;
                connect(map, next, visited);
            }
        }
    }
}
