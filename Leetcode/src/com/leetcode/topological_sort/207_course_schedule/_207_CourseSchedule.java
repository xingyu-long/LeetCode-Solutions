package com.leetcode.topological_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _207_CourseSchedule {

    /**
     * 207. Course Schedule When: 2019/06/20 review1:2019/9/11
     * <p>
     * solution:使用BFS实现 可以使用dfs，类似于检查图是否有cycle 这里是类似于有向图！
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */

    // time:O(V + E) v次遍历 e次递归 space:O(n)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // [a, b] take b first and then a.
        int[] indegree = new int[numCourses];
        List<Integer>[] graph = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] req : prerequisites) {
            indegree[req[0]]++;
            graph[req[1]].add(req[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int total = numCourses;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            total--;
            for (int adj : graph[curr]) {
                indegree[adj]--;
                if (indegree[adj] == 0) {
                    queue.offer(adj);
                }
            }
        }
        return total == 0;
    }
}
