package com.leetcode.TopologicalSort;

public class _261_GraphValidTree {

    // 使用无向图的检测 都可以


    // union find

    /**
     * 261. Graph Valid Tree
     * Time:11/2/2019
     * @param n
     * @param edges
     * @return
     */
    public static boolean validTree(int n, int[][] edges) {
        if (n == 1 && edges.length == 0) return true;
        if (n < 1 || edges == null) return false;

        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = -1;
        }

        for (int[] pair : edges) {
            int x = find(roots, pair[0]);
            int y = find(roots, pair[1]);
            if (x == y) return false;
            roots[x] = y;
        }
        return true;
    }

    private static int find(int[] roots, int i) {
        while (roots[i] != -1) {
            i = roots[i];
        }
        return i;
    }

    // 这个union find表示 i = x的root是多少
    // 并且这样的连接表示1连接2，2连接3，目前1连接3 肯定有环了！
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        int n = 5;
        System.out.println(validTree(n, edges));
    }
}
