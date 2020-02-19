package com.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInUndirectedGraph {
    public HashMap<Integer, HashSet<Integer>> adjs;
    public int numOfNode;
    public CycleInUndirectedGraph() {
        adjs = new HashMap<>();
        numOfNode = 0;
    }

    public int getNumOfNode() {
        return adjs.size();
    }

    public void add(int num1, int num2) {
        if (!adjs.containsKey(num1)) {
            HashSet<Integer> set = new HashSet<>();
            set.add(num2);
            adjs.put(num1, set);
        } else {
            adjs.get(num1).add(num2);
        }
        if (!adjs.containsKey(num2)) {
            HashSet<Integer> set = new HashSet<>();
            set.add(num1);
            adjs.put(num2, set);
        } else {
            adjs.get(num2).add(num1);
        }
    }

    public boolean hasCycleByDFS() {
        if (adjs.isEmpty()) return false;
        HashSet<Integer> visited = new HashSet<>();
        for (int v : adjs.keySet()) {
            if (visited.contains(v)) continue;
            boolean cycle = helper(visited, v, v);
            if (cycle) return true;
        }
        return false;
    }

    public boolean helper(HashSet<Integer> visited, int v, int parent) {
        visited.add(v);
        for (int adj : adjs.get(v)) {
            if (adj == parent) continue;
            if (visited.contains(adj)) return true;
            if (helper(visited, adj, v)) return true;
        }
        return false;
    }
    public boolean hasCycleByBFS() {
        HashSet<Integer> visited = new HashSet<>();
        for (int v : adjs.keySet()) {
            if (visited.contains(v)) continue;
            if (bfs(v, visited)) return true;
        }
        return false;
    }
    public boolean bfs(int v, HashSet<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[getNumOfNode()];
        queue.offer(v);
        visited.add(v);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int adjacent : adjs.get(cur)) {
                if (!visited.contains(adjacent)) {
                    visited.add(adjacent);
                    parent[adjacent] = cur;
                    queue.offer(adjacent);
                } else if (parent[cur] != adjacent) { // 表示在访问过这个节点的情况
                    return true;
                }
            }
        }
        return false;
    }

    // 也可以使用Disjoint-set方法 应该是union find吧？
    public static void main(String[] args) {
        CycleInUndirectedGraph undirectedGraph = new CycleInUndirectedGraph();
        undirectedGraph.add(1, 2);
        undirectedGraph.add(2, 3);
        undirectedGraph.add(2, 4);
        undirectedGraph.add(3, 5);
//        undirectedGraph.add(4, 5);
        System.out.println(undirectedGraph.hasCycleByDFS());
    }
}
