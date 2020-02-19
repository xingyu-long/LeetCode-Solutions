package com.leetcode.graph;

import java.util.*;

public class _1311_GetWatchedVideosbyYourFriends {
    // 这里的friend表已经给出了graph的关系，不用构建邻接表
    // time:O(nlogn) space:O(n)
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        visited[id] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        // 相当于每次替换当前层。
        for (int i = 0; i < level; i++) {
            Queue<Integer> newQ = new LinkedList<>();
            for (int friend : queue) {
                for (int adj : friends[friend]) {
                    if (!visited[adj]) {
                        visited[adj] = true;
                        newQ.offer(adj);
                    }
                }
            }
            queue = newQ;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int person : queue) {
            for (String video : watchedVideos.get(person)) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }
        List<String> res = new ArrayList<>(map.keySet());
        res.sort((a, b) -> {
            int fa = map.get(a);
            int fb = map.get(b);
            if (fa != fb) {
                return fa - fb;
            } else {
                return a.compareTo(b);
            }
        });
        return res;
    }
}
