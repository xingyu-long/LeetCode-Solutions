package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _630_CourseScheduleIII {
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0 || courses[0] == null || courses[0].length == 0) {
            return 0;
        }
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        int time = 0;
        for (int[] c : courses) {
            time += c[0];
            pq.offer(c[0]);
            if (time > c[1]) {
                time -= pq.peek();
                pq.poll();
            }
        }
        return pq.size();
    }
}
