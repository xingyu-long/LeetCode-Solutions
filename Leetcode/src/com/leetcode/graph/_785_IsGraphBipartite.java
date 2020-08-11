package com.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/14/2020
 * @Description: Graph, BFS, DFS
 **/
public class _785_IsGraphBipartite {
    // colors数组其实也算作为visited数组
    // time:O(V + E) space: O(n)
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue;
            // if (!bfs(graph, colors, i)) {
            //     return false;
            // }
            if (!dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }
    // 3 <-> 1
    private boolean bfs(int[][] graph, int[] colors, int curr) {
        Queue<Integer> queue = new LinkedList<>();
        // 顺序的问题，先染上
        queue.offer(curr);
        colors[curr] = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int nextColor = colors[node] * -1;
            for (int adj : graph[node]) {
                if (colors[adj] == 0) {
                    queue.offer(adj);
                    colors[adj] = nextColor;
                } else if (colors[adj] != nextColor) {
                    // System.out.println("color = " + colors[adj] + " nextColor = " + nextColor);
                    return false;// 无法分成符合条件的情况
                }
            }
        }
        return true;
    }
    // 这里的color相当于是赋值
    // 所以赋值之前需要检查
    private boolean dfs(int[][] graph, int[] colors, int curr, int color) {
        if (colors[curr] != 0) {
            return color == colors[curr];
        }
        colors[curr] = color;
        for (int adj : graph[curr]) {
            int nextColor = color * -1;
            if (!dfs(graph, colors, adj, nextColor)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        _785_IsGraphBipartite isGraphBipartite = new _785_IsGraphBipartite();
        System.out.print(isGraphBipartite.isBipartite(graph));
    }
}
