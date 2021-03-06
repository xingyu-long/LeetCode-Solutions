package com.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 05/01/2020
 * @Description: Graph, Strong Connected Component
 **/
public class _1192_CriticalConnectionsinaNetwork {

    private int time = 0;

    // time:O(V + E) space:O(n)
    // https://www.youtube.com/watch?v=aZXi1unBdJA
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 删除某一条然后进行遍历看是否可以达到所有点？
        if (connections == null || connections.size() == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer>[] graph = new List[n];
        buildGraph(connections, graph);

        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parents = new int[n];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parents, -1);

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) { // 访问没有访问过的点
                dfs(i, parents, disc, low, graph, res);
            }
        }
        return res;
    }

    private void dfs(int from, int[] parents, int[] disc, int[] low, List<Integer>[] graph,
        List<List<Integer>> res) {
        if (disc[from] != -1) {
            return;// 表明已经访问过了
        }

        low[from] = disc[from] = time++;
        for (int to : graph[from]) {
            if (disc[to] == -1) {
                parents[to] = from;
                dfs(to, parents, disc, low, graph, res);
                low[from] = Math.min(low[from], low[to]);
                if (disc[from] < low[to]) {
                    // System.out.println("u = " + u + " v = " + v);
                    res.add(Arrays.asList(from, to));
                }
            } else if (parents[from] != to) { // 有其他边能够到达这个点，然后我们可能形成更小的一个堆，这里用disc去比较
                low[from] = Math.min(low[from], disc[to]);
            }
        }
    }


    private void buildGraph(List<List<Integer>> connections, List<Integer>[] graph) {
        for (List<Integer> con : connections) {
            int u = con.get(0);
            int v = con.get(1);
            if (graph[u] == null) {
                graph[u] = new ArrayList<>();
            }
            if (graph[v] == null) {
                graph[v] = new ArrayList<>();
            }
            graph[u].add(v);
            graph[v].add(u);
        }
    }
}
