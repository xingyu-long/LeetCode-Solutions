package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class _1298_MaximumCandiesYouCanGetfromBoxes {
    // BFS
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        // 表示拥有的钥匙以及箱子
        HashSet<Integer> hasKeys = new HashSet<>();
        HashSet<Integer> hasBoxes = new HashSet<>();
        // 初始化所拥有的的key(其实就是那些status[i] = 1的开着的)
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 1) hasKeys.add(i);
        }
        Queue<Integer> queue = new LinkedList<>();// 表示既有钥匙又有箱子的时候。
        for (int box : initialBoxes) {
            hasBoxes.add(box);
            if (hasKeys.contains(box)) queue.offer(box);
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int box = queue.poll();
            res += candies[box];
            // check if there have boxes in it. and if there have keys we can add to the queue.
            for (int boxInIt : containedBoxes[box]) {
                hasBoxes.add(boxInIt);
                if (hasKeys.contains(boxInIt)) queue.offer(boxInIt);
            }

            // check if there have other keys. and if there have boxes we can add to the queue.
            for (int keyInIt : keys[box]) {
                // 查看当前没有这个key然后有箱子才加入，否则会TLE
                if (!hasKeys.contains(keyInIt) && hasBoxes.contains(keyInIt)) queue.offer(keyInIt);
                hasKeys.add(keyInIt);
            }
        }
        return res;
    }

}
