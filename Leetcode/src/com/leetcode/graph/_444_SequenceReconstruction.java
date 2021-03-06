package com.leetcode.graph;

import java.util.*;

public class _444_SequenceReconstruction {
    // 利用构建有向图的关系，然后使用org作为唯一验证。使用BFS。
    // time:O(|seqs| + V + E)
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0) return false;

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> indegree = new HashMap<>();

        int n = org.length;
        for (List<Integer> list : seqs) {
            if (list.size() == 1) {
                int a = list.get(0);
                if (!map.containsKey(a)) {
                    map.put(a, new HashSet<>());
                    indegree.put(a, indegree.getOrDefault(a, 0));
                }
            } else if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    int a = list.get(i - 1);
                    int b = list.get(i);
                    // 表示当前的没有被加入过
                    if (!map.containsKey(a)) {
                        map.put(a, new HashSet<>());
                        indegree.put(a, indegree.getOrDefault(a, 0));
                    }
                    // 表示当前没有加入b这个边的话就加入
                    if (!map.get(a).contains(b)) {
                        map.get(a).add(b);
                        indegree.put(b, indegree.getOrDefault(b, 0) + 1);
                    }
                }
            }
        }
        if (indegree.size() != n) return false;// 应该包含每个元素
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) queue.offer(key);
        }
        int index = 0;
        while (!queue.isEmpty()) {
            // 只有唯一的一条路。
            if (queue.size() > 1) return false;
            int curr = queue.poll();
            if (index == n || org[index++] != curr) return false;
            if (map.containsKey(curr)) {
                for (int next : map.get(curr)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return n == index;
    }
}
