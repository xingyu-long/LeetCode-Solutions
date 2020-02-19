package com.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;

public class CycleInDirectedGraph {
    public HashMap<Integer, HashSet<Integer>> adjs;
    public int numOfNode;
    public CycleInDirectedGraph() {
        adjs = new HashMap<>();
        numOfNode = 0;
    }

    public int getNumOfNode() {
        return adjs.size();
    }
    public void addTo(int num1, int num2) {
        if (!adjs.containsKey(num2)) {
            HashSet<Integer> set = new HashSet<>();
            set.add(num1);
            adjs.put(num2, set);
        } else {
            adjs.get(num2).add(num1);
        }

        if (!adjs.containsKey(num1)) {
            HashSet<Integer> set = new HashSet<>();
            adjs.put(num1, set);
        }

    }

    public boolean hasCycleByDFS() {
        if (adjs.isEmpty()) return false;
        HashSet<Integer> whiteSet = new HashSet<>(); // unvisited
        HashSet<Integer> graySet = new HashSet<>(); // visiting
        HashSet<Integer> blackSet = new HashSet<>(); // visited
        for (int v : adjs.keySet()) {
            whiteSet.add(v);
        }

        while (whiteSet.size() > 0) {
            int v = whiteSet.iterator().next();
            if (helper(v, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    public boolean helper(int v, HashSet<Integer> whiteSet, HashSet<Integer> graySet, HashSet<Integer> blackSet) {
        moveVertex(v, whiteSet, graySet);
        for (int adj : adjs.get(v)) {
            if (blackSet.contains(adj)) continue;
            if (graySet.contains(adj)) return true;
            if (helper(adj, whiteSet, graySet, blackSet)) return true;
        }
        moveVertex(v, graySet, blackSet);
        return false;
    }

    public void moveVertex(int v, HashSet<Integer> sourceSet, HashSet<Integer> destinationSet) {
        sourceSet.remove(v);
        destinationSet.add(v);
    }

    public static void main(String[] args) {
        CycleInDirectedGraph directedGraph = new CycleInDirectedGraph();
        directedGraph.addTo(2, 1);
        directedGraph.addTo(3, 2);
        directedGraph.addTo(4, 3);
        directedGraph.addTo(3, 4);
        System.out.println(directedGraph.hasCycleByDFS());
    }
}
