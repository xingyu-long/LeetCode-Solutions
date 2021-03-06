package com.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 05/27/2020
 * @Description: Graph, Bipartite
 **/
public class _886_PossibleBipartition {
    // 基本和785一样
    // time:O(V + E)
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<Integer>[] graph = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            graph[dis[0]].add(dis[1]);
            graph[dis[1]].add(dis[0]);
        }
        int[] colors = new int[N + 1];
        // 0: init, -1: red, 1: blue;
        for (int i = 1; i <= N; i++) {
            if (colors[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                int currColor = colors[curr];
                int nextColor = -1 * currColor;
                for (int adj : graph[curr]) {
                    if (colors[adj] == 0) {
                        queue.offer(adj);
                        colors[adj] = nextColor;
                    } else if (colors[adj] != nextColor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
