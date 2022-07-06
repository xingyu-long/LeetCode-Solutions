package com.leetcode.TopologicalSort;


import java.util.LinkedList;
import java.util.Queue;

public class _210_CourseScheduleII {

    /**
     *  210. Course Schedule II
        When: 2019/06/20

        solution: 思路与前面一致，但只是需要poll出来的时候保存一下
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //依然使用BFS的方法 并且利用ArrayList来添加
        int[] indegree = new int[numCourses];
        int num = numCourses;

        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        int[] res = new int[num];

        Queue<Integer> queue = new LinkedList<>();
        //度为0的时候 入queue
        for (int i = 0; i < num; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res[count++] = pre;
            for (int[] pair : prerequisites) {
                if (pair[1] == pre) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        // list.stream().mapToInt(i->i).toArray()
        return count == num ? res : new int[]{};
    }
}
