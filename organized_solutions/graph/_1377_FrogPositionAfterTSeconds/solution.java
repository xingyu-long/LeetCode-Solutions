package com.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1377_FrogPositionAfterTSeconds {

    // time: O(n) space:O(n)
    // https://leetcode.com/problems/frog-position-after-t-seconds/discuss/532505/Java-Straightforward-BFS-Clean-code-O(N)
    public double frogPosition(int n, final int[][] edges, int t, final int target) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (final int [] e : edges) {
            graph[e[0] - 1].add(e[1] - 1);
            graph[e[1] - 1].add(e[0] - 1);
        }
        final boolean[] visited = new boolean[n];
        visited[0] = true;
        final double[] prob = new double[n];
        prob[0]  = 1.0;
        final Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        // 要考虑到frog不能跳，只有没有选择的时候，不能跳的时候才能留在原点跳完并且保留其概率
        // 并且我们一定要跳完t下，如果中途遇到，也会跳走。
        while (!queue.isEmpty() && t-- > 0) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final int curr = queue.poll();
                int adjCount = 0;
                for (int adj : graph[curr]) if (!visited[adj]) adjCount++;
                for (int adj : graph[curr]) {
                    if (!visited[adj]) {
                        visited[adj] = true;
                        queue.offer(adj);
                        prob[adj] = prob[curr] / adjCount;
                    }
                }
                // 很关键，我们只需要上面保留下一层子节点的概率值就好
                if (adjCount > 0) prob[curr] = 0;// 因为不是目标地方，还会继续走
            }
        }
        return prob[target - 1];
    }
}