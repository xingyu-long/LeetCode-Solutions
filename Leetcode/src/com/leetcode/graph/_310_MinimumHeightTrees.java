package com.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _310_MinimumHeightTrees {

    /**
     *  310. Minimum Height Trees
     *  When: 2019/7/2

        solution:
        使用BFS并且层层剥离 寻找最后剩下的节点，即根节点。
     * @param n
     * @param edges
     * @return
     */

    // time: O(n) space: O(n)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();//保存结果，以及一开始的各种叶子节点（待删除）
        if (n == 1) {
            res.add(0);
            return res;
        }

        //构建图的关系（使用hashset）
        List<HashSet<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        //相当于先加入外层叶子节点
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                res.add(i);
            }
        }

        while (n > 2) {
            n -= res.size();
            List<Integer> leaves = new ArrayList<>();
            for (int i : res) {
                for (int j : adj.get(i)) {
                    adj.get(j).remove(i);//删除外层叶子节点
                    if (adj.get(j).size() == 1) {
                        leaves.add(j);
                    }
                }
            }
            res = leaves;
        }
        return res;
    }
}
