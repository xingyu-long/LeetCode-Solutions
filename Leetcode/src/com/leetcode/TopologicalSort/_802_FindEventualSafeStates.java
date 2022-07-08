package com.leetcode.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _802_FindEventualSafeStates {
    // 从terminal node出发，倒着走，并且移除Graph里面的关系，如果其出度为0则可以作为safe node
    // time: O(len(graph) + Edge) space: O(len(graph))
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer>[] Graph = new List[n];
        List<Integer>[] revGraph = new List[n];
        boolean[] safe = new boolean[n];

        for (int i = 0; i < n; i++) {
            Graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int[] node = graph[i];
            // 说明这些点是terminal node
            if (node == null || node.length == 0) {
                queue.offer(i);
            } else {
                for (int next : node) {
                    Graph[i].add(next);
                    revGraph[next].add(i);
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            safe[curr] = true;
            for (int prev : revGraph[curr]) {
                // remove curr obj.
                Graph[prev].remove(Integer.valueOf(curr));
                // 其出度为0，所以prev是safe node
                if (Graph[prev].size() == 0) {
                    queue.offer(prev);
                }
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]) res.add(i);
        }
        return res;
    }
    

    // 可以转化成环的问题
    // time: O(V + E)
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        // 0 white, not visited
        // 1 gray, visiting
        // 2 black, visited
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (noCycle(graph, i, color)) {
                res.add(i);
            }
        }
        return res;
    }
    
    public boolean noCycle(int[][] graph, int curr, int[] color) {
        if (color[curr] != 0) {
            return color[curr] == 2;
        }
        color[curr] = 1;
        for (int next : graph[curr]) {
            if (!noCycle(graph, next, color)) {
                return false;
            }
        }
        color[curr] = 2;
        return true;
    }
}
