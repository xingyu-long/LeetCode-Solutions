package com.leetcode.TopologicalSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _310_MinimumHeightTrees {
    // time: O(n) space: O(n)
    // undirected graph
    // 这是模拟 拓扑排序，不完全是，这里的adj需要删除最外层的，然后在size == 1加上这个
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();//保存结果，以及一开始的各种叶子节点（待删除）
        if (n == 1) {
            res.add(0);
            return res;
        }

        //构建图的关系（使用hashset）
        List<HashSet<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        //相当于先加入外层叶子节点
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                res.add(i);
            }
        }
        // 其实接近的就是course schedule.
        while (n > 2) { // 因为只有剩2个以内的时候，不管选择哪个点 都会形成最小高度的树
            n -= res.size();
            List<Integer> leaves = new ArrayList<>();
            for (int toBeDeleted : res) {
                // 这里用的太妙了了！！！！！！
                for (int adj : graph.get(toBeDeleted)) { // 这里表示删除那些含有外部叶子节点的记录。
                    graph.get(adj).remove(toBeDeleted);//删除外层叶子节点
                    if (graph.get(adj).size() == 1) { // 这个1还是删除倒数第二个节点的时候加入的。
                        leaves.add(adj);
                    }
                }
            }
            res = leaves;
        }
        return res;
    }

    // 也可以使用map来搭建
}
