package com.leetcode.stack_priority_queue.monoStack;

import java.util.Stack;

public class _456_132Pattern {
    // time:O(n) space: O(n)
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        // i < j < k
        // nums[i] < nums[k] < nums[j]
        // nums[k]
        int third = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) return true;
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                third = nums[stack.peek()];
                stack.pop();
            }
            stack.push(i);
        }
        return false;
    }
}
