package com.pramp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @Date: 06/21/2020
 * @Description: Topological Sort
 **/
public class _Jun_21_2020_CourseSchedule {

    // time:(N) space:O(n)
    private static List<String> courseSchedule(String[][] courses) {
        if (courses == null || courses.length == 0 || courses[0] == null
            || courses[0].length == 0) {
            return new ArrayList<>();
        }
        Map<String, String> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        //  HashSet<String> all = new HashSet<>();
        String startCourse = "";
        // O(n)
        for (String[] course : courses) {
            String u = course[0], v = course[1];
            graph.put(u, v);
            indegree.put(v, indegree.getOrDefault(v, 0) + 1);
        }
        // 1 -> 2(1)
        for (String[] course : courses) {
            String u = course[0];
            if (!indegree.containsKey(u)) {
                startCourse = u;
                break;
            }
        }
        // one element left;
        // startCourse = all.iterator().next();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startCourse);
        List<String> res = new ArrayList<>();
        // O(n)
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            res.add(curr);
            if (!graph.containsKey(curr)) {
                continue;
            }
            String next = graph.get(curr);
            indegree.put(next, indegree.get(next) - 1);
            if (indegree.get(next) == 0) {
                indegree.remove(next);
                queue.offer(next);
            }
        }
        return res;
    }

    public void main(String args[]) {
        String[][] courses = {{"Data Structures", "Algorithms"},
            {"Foundations of Computer Science", "Operating Systems"},
            {"Computer Networks", "Computer Architecture"},
            {"Algorithms", "Foundations of Computer Science"},
            {"Computer Architecture", "Data Structures"},
            {"Software Design", "Computer Networks"}};
        List<String> res = courseSchedule(courses);
        for (String course : res) {
            System.out.println(course);
        }
        System.out.println("Practice makes Perfect!");
    }
}
