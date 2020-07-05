package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/01/2020, 06/17/2020
 * @Description: Tree
 **/
public class _1245_TreeDiameter {

    private int res;

    public int treeDiameter(int[][] edges) {
        // traversal problem
        // 那就看成遍历问题 DFS 依次从每个点出发，求最长的情况 -> 时间复杂度是n^2 TLE
        // 一开始有想到以前的tree做法，即左右两边去长的就可以（但是想了想感觉太复杂就没继续做
        // 下面的解法，就把看成tree的遍历，并且也不用visited数组就可以完成，因为会一直向下遍历
        // 然后从0开始（看做root节点），因为更新结果是全局的，所以不影响。
        // 整体来说，当时没有很好的动脑去思考。
        int n = edges.length;
        List<Integer>[] graph = buildGraph(edges, n);
        res = 0;
        dfs(0, graph, -1);
        return res;
    }

    private List<Integer>[] buildGraph(int[][] edges, int n) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        return graph;
    }

    private int dfs(int curr, List<Integer>[] graph, int parent) {
        // if (visited[curr]) return -1;
        // visited[curr] = true;
        int maxDepth = 0, secondMax = 0;
        for (int adj : graph[curr]) {
            if (adj == parent) {
                continue;
            }
            int next = dfs(adj, graph, curr);
            // 这个是关键。这里还是没有注意到
            if (next > maxDepth) {
                secondMax = maxDepth;
                maxDepth = next;
            } else if (next > secondMax) { // 小于最大的，但是大于第二大的情况，就更新
                secondMax = next;
            }
        }
        res = Math.max(res, maxDepth + secondMax);
        return maxDepth + 1;
    }
}
