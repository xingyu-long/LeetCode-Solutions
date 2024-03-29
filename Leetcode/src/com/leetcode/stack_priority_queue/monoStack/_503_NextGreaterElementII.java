package com.leetcode.stack_priority_queue.monoStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Date: 06/11/2020
 * @Description: Mono stack
 **/
public class _503_NextGreaterElementII {
    // 自己写的方法，还可以优化成下面的那种
    // 初始化-1 之前没有想到
    public int[] nextGreaterElements(int[] nums) {
        // [1, 2, 1, 1, 2, 1]
        //
        // map<Index, nextGreater>
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Stack<Integer> indexes = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);  // 初始化为-1
        for (int i = 0; i < nums.length * 2; i++) {
            int index = i % nums.length;
            while (!stack.isEmpty() && nums[index] > stack.peek()) {
                int resIndex = indexes.pop();
                stack.pop();
                if (resIndex < nums.length) {
                    res[resIndex] = nums[index];
                }
            }
            indexes.push(i);
            stack.push(nums[index]);
        }
        return res;
    }

    // stack存index即可，可以省去index的Map
    public int[] nextGreaterElements2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);  // 初始化为-1
        for (int i = 0; i < nums.length * 2; i++) {
            int index = i % nums.length;
            while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
                res[stack.pop()] = nums[index];
            }
            stack.push(index);
        }
        return res;
    }
}
