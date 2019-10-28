package com.intern.Roblox;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMinMax {

    // 可能求sliding window里min然后求最大
    public int maxSlidingWindow3(int[] nums, int k) {
        // corner cases.
        if (nums == null || nums.length == 0 || k == 0) return Integer.MAX_VALUE;

        // store index (nums[index] in descending order)
        Deque<Integer> deque = new LinkedList<>();
        // result array
        int[] min = new int[nums.length - k + 1];
        // int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // check if it out of bound (should be in  [i - (k-1), i] eg: i = 3, left:1)
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            // pop it if this value is less than the before.
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);
            if (i >= k - 1) {
                min[i - k + 1] = nums[deque.peek()];
            }
        }
        // get max
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            max = Math.max(max, min[i]);
        }
        System.out.println(max);
        return max;
    }
}
