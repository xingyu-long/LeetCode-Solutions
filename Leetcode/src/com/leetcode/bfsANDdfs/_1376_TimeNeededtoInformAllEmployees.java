package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1376_TimeNeededtoInformAllEmployees {

    class Node {
        int id;
        int time;
        
        public Node (int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    /**
     * When: 03/08/2020
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    // 需要用hashmap先保存父子关系。不用保持leaf节点的值再取最大，直接每次poll出来的时候比较就行。
    // time: O(n) space:O(n)
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Queue<Node> queue = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // 比赛的时候没有用这个做优化
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(manager[i], new ArrayList<>());
            map.get(manager[i]).add(i);
        }
        queue.offer(new Node(headID, 0));
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                int id = node.id;
                res = Math.max(res, node.time); // 这里直接比较得到最大。相当于每次都在操作。
                List<Integer> nexts = map.getOrDefault(id, new ArrayList<>());
                if (nexts != null) {
                    for (int next : nexts) {
                        queue.offer(new Node(next, node.time + informTime[id]));
                    }
                }
            }
        }
        return res;
    }
}