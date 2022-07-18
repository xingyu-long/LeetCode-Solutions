/*
 * @Date: 07/17/2022 16:00:22
 * @LastEditTime: 07/17/2022 16:01:48
 * @Description: You need to fill out
 */
package com.leetcode.dynamicProgramming;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _818_RaceCar {
    public class Node {
        int position;
        int speed;

        public Node(int p, int s) {
            this.position = p;
            this.speed = s;
        }
    }

    // 利用BFS缩小其搜索空间
    // time: O(target * log(target))
    // space: O(target * log(target))
    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 1));
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr.position == target) {
                    return step;
                }

                // A (accelerate)
                int nextPos = curr.position + curr.speed;
                int nextSpeed = curr.speed * 2;
                if (!visited.contains(nextPos + ", " + nextSpeed) && Math.abs(nextPos - target) < target) {
                    visited.add(nextPos + ", " + nextSpeed);
                    queue.offer(new Node(nextPos, nextSpeed));
                }

                // R (reverse)
                nextPos = curr.position;
                nextSpeed = curr.speed > 0 ? -1 : 1;
                if (!visited.contains(nextPos + ", " + nextSpeed) && Math.abs(nextPos - target) < target) {
                    visited.add(nextPos + ", " + nextSpeed);
                    queue.offer(new Node(nextPos, nextSpeed));
                }
            }
            step++;
        }
        return -1;
    }
}
