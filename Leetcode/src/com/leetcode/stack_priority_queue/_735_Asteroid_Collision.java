package com.leetcode.stack_priority_queue;

import java.util.Deque;
import java.util.LinkedList;

public class _735_Asteroid_Collision {
    // 主要是分情况讨论
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[]{};
        }
        int n = asteroids.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int size = asteroids[i];
            // 为空的情况下，随便放进来
            if (deque.isEmpty()) {
                deque.offer(size);
            } else {
                // 当前大于0，或者是最后一个也小于0，表示不会碰撞。
                if (size > 0 || deque.peekLast() < 0) {
                    deque.offer(size);
                } else if (Math.abs(deque.peekLast()) <= Math.abs(size)) {
                    // 为负数的情况，并且可以碰撞：表明当前deque最后一个是正数，size为负数。
                    if (Math.abs(deque.peekLast()) < Math.abs(size)) i--;
                    deque.pollLast();
                }
            }
        }
        return deque.stream().mapToInt(i->i).toArray();
    }
}
