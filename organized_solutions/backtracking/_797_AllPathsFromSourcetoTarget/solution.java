/*
 * @Date: 07/24/2020 17:59:37
 * @LastEditTime: 11/27/2021 20:53:45
 * @Description: Backtracking
 */
package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _797_AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0 || graph[0] == null || graph[0].length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        build(graph, 0, res, new ArrayList<>());
        return res;
    }

    private void build(int[][] graph, int current, List<List<Integer>> res, List<Integer> path) {
        path.add(current);
        if (current == graph.length - 1) {
            res.add(new ArrayList<>(path));
        }

        int[] adjs = graph[current];
        if (adjs != null) {
            for (int adj : adjs) {
                build(graph, adj, res, path);
            }
        }
        path.remove(path.size() - 1);
    }
}
