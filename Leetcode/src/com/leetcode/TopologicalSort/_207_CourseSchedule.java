package com.leetcode.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _207_CourseSchedule {

    /**
     * 207. Course Schedule
     * When: 2019/06/20
     * review1:2019/9/11
     * <p>
     * solution:使用BFS实现
     * 可以使用dfs，类似于检查图是否有cycle 这里是类似于有向图！
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
            if (indegree[i] == 0) { // 注意这里的i，因为course都是0~n-1 所以直接用i代表某门课
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
