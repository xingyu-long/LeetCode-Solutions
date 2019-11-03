package com.leetcode.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleUndirectedGraph {
    int numOfNode;
    List<List<Integer>> adj;

    public DetectCycleUndirectedGraph(int num) {
        numOfNode = num;
        adj = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(List<List<Integer>> adj, int p, int q) {
        adj.get(p).add(q);
    }

    public boolean bfs(int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[numOfNode];
        queue.offer(node);
        visited[node] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int adjacent : adj.get(cur)) {
                if (!visited[adjacent]) {
                    visited[adjacent] = true;
                    parent[adjacent] = cur;
                    queue.offer(adjacent);
                } else if (parent[cur] != adjacent) { // 表示在访问过这个节点的情况
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(boolean[] visited, boolean[] duplicate, int node) {
        if (duplicate[node]) return true;
        if (!visited[node]) {
            duplicate[node] = true;
            visited[node] = true;
            for (int adjacent : adj.get(node)) {
                if (dfs(visited, duplicate, adjacent)) return true;
            }
            duplicate[node] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCycleUndirectedGraph undirectedGraph = new DetectCycleUndirectedGraph(4);
        undirectedGraph.addEdge(undirectedGraph.adj, 0, 1);
        undirectedGraph.addEdge(undirectedGraph.adj, 1, 2);
//        undirectedGraph.addEdge(undirectedGraph.adj, 2, 0);
        undirectedGraph.addEdge(undirectedGraph.adj, 2, 3);
        boolean[] visited = new boolean[4];
        boolean[] duplicate = new boolean[4];
//        for (int i = 0; i < 4; i++) {
//            if (undirectedGraph.dfs(visited, duplicate, i)) {
//                System.out.println("1- This is a cycle");
//                break;
//            }
//        }

        System.out.println(undirectedGraph.bfs(0, visited));
        for (int i = 0; i < 4; i++) {
            if (!visited[i] && undirectedGraph.bfs(i, visited)) {
                System.out.println("2- This is a cycle");
                break;
            }
        }
    }
}
