package com.ctci.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;


public class _4_1_RouteBetweenNodes {

    private int number;
    private LinkedList<Integer> adj[];
    public boolean[] visited;

    _4_1_RouteBetweenNodes(int number) {
        this.number = number;
        adj = new LinkedList[number];
        for (int i = 0; i < number; i++) {
            adj[i] = new LinkedList<>();
        }
        visited = new boolean[this.number];
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // 利用BFS
    public boolean bfs(int s, int d) {

        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int edge : adj[cur]) {
                if (edge == d) return true;
                if (!visited[edge]) {
                    visited[edge] = true;
                    queue.add(edge);
                }
            }
        }
        return false;
    }

    public boolean dfs(int s, int d) {
        visited[s] = true;
        for (int edge : adj[s]) {
            if (edge == d) return true;
            if (!visited[edge]) {
                visited[edge] = true;
                return dfs(edge, d);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        _4_1_RouteBetweenNodes graph = new _4_1_RouteBetweenNodes(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);
        int u = 0, v = 3;
        System.out.println("the result of DFS = " + graph.dfs(u, v));
        System.out.println("the result of BFS = " + graph.bfs(u, v));
    }
}
