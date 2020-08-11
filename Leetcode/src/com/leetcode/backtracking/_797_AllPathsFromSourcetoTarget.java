package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 07/24/2020
 * @Description: backtracking
 **/
public class _797_AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // backtracking
        if (graph == null || graph.length == 0 || graph[0] == null || graph[0].length == 0) {
            return new ArrayList<>();
        }
        int m = graph.length;
        List<List<Integer>> res = new ArrayList<>();
        build(res, new ArrayList<>(), 0, graph);
        return res;
    }

    private void build(List<List<Integer>> res, List<Integer> list, int curr, int[][] graph) {
        if (curr == graph.length - 1) {
            list.add(curr);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }

        int[] adjs = graph[curr];
        if (adjs != null) {
            list.add(curr);
            for (int next : adjs) {
                build(res, list, next, graph);
            }
            list.remove(list.size() - 1);
        }
    }
}
