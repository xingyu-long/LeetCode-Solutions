/*
 * @Date: 03/19/2021 09:04:40
 * @LastEditTime: 03/19/2021 09:06:17
 * @Description: BFS
 */
package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _841_KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        int n = rooms.size();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int curr = queue.poll();
                List<Integer> nexts = rooms.get(curr);
                if (nexts == null)
                    continue;
                for (int next : nexts) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return visited.size() == n;
    }
}
