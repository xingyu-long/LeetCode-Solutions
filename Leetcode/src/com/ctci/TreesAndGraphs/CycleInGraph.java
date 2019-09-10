package com.ctci.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CycleInGraph {

    private int number;
    private List<List<Integer>> adj;

    public CycleInGraph(int number) {
        this.number = number;
        adj = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public boolean dfs(int i, boolean[] visited, boolean[] inStack) {
        // 如果在栈中 则返回找到cycle
        if (inStack[i]) return true;
        if (!visited[i]) {
            visited[i] = true;
            inStack[i] = true;
            for (int node : adj.get(i)) {
                if (dfs(node, visited, inStack)) {
                    return true;
                }
            }
            inStack[i] = false;
        }
        return false;
    }

    public void addEdge(int s, int d) {
        adj.get(s).add(d);
    }

    public boolean isCycle() {
        boolean[] visited = new boolean[this.number];
        boolean[] inStack = new boolean[this.number];

        for (int i = 0; i < number; i++) {
            if (dfs(i, visited, inStack)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CycleInGraph cycle = new CycleInGraph(4);
        cycle.addEdge(0, 1);
        cycle.addEdge(0, 2);
        cycle.addEdge(1, 2);
        cycle.addEdge(2, 0);
        cycle.addEdge(2, 3);
        cycle.addEdge(3, 3);
        System.out.println(cycle.isCycle());
    }
}
