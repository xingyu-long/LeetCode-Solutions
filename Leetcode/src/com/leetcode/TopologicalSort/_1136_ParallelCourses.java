package com.leetcode.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 06/27/2020
 * @Description: TODO
 **/
public class _1136_ParallelCourses {
    // time: O(V + E) space: O(n)
    public int minimumSemesters(int n, int[][] dependencies) {
        List<Integer>[] graph = new List[n + 1];
        int[] indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] dep : dependencies) {
            int u = dep[0], v = dep[1];
            graph[u].add(v);
            indegree[v]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        // 需要注意是从1开始遍历
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                n--;
                List<Integer> adjs = graph[curr];
                if (adjs.size() > 0) {
                    for (int next : adjs) {
                        indegree[next]--;
                        if (indegree[next] == 0) {
                            queue.offer(next);
                        }
                    }
                }
            }
            count++;
        }
        return n == 0 ? count : -1;
    }
}
