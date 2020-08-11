package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 04/23/2020
 * @Description: BFS
 **/
public class _752_OpentheLock {

    // 注意的问题：1. 需要加入visited，这样防止重复
    // 2. 每次poll出来需要看lock里面有没有！
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }
        HashSet<String> lock = new HashSet<>();
        // BFS
        for (String end : deadends) {
            lock.add(end);
        }

        int level = 0;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (lock.contains(curr)) {
                    continue;
                }
                if (curr.equals(target)) {
                    return level;
                }
                for (int j = 0; j < 4; j++) {
                    StringBuilder sb = new StringBuilder(curr);
                    char ch = curr.charAt(j);
                    // 右转
                    String s1 =
                        sb.substring(0, j) + (ch == '9' ? 0 : ch - '0' + 1) + sb.substring(j + 1);
                    // 左转
                    String s2 =
                        sb.substring(0, j) + (ch == '0' ? 9 : ch - '0' - 1) + sb.substring(j + 1);
                    if (!lock.contains(s1) && !visited.contains(s1)) {
                        queue.offer(s1);
                        visited.add(s1);
                    }
                    if (!lock.contains(s2) && !visited.contains(s2)) {
                        queue.offer(s2);
                        visited.add(s2);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
