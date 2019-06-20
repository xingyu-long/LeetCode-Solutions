package com.leetcode.TopologicalSort;

import java.util.LinkedList;
import java.util.Queue;

public class _207_CourseSchedule {

    /**
     *  207. Course Schedule
        When: 2019/06/20

        solution:使用BFS实现
     * @param numCourses
     * @param prerequisites
     * @return
     */

    // time:O(V + E) v次遍历 e次递归 space:O(n)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int res = numCourses;
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res--;
            for (int[] pair : prerequisites) {
                if (pair[1] == pre) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        return res == 0;
    }
}
