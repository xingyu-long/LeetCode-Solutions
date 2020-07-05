package com.leetcode.graph.UnionFind;

public class _547_FriendCircles {

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
                dfs(M, i, n, visited);
            }
        }
        return res;
    }

    public void dfs(int[][] M, int i, int n, boolean[] visited) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && M[i][j] == 1) {
                dfs(M, j, n, visited);
            }
        }
    }
}
