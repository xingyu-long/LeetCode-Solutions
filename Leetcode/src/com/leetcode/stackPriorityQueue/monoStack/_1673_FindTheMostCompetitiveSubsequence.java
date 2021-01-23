/*
 * @Date: 01/21/2021 10:21:56
 * @LastEditTime: 01/21/2021 10:22:33
 * @Description: Mono stack (increasing)
 */
package com.leetcode.stackPriorityQueue.monoStack;

import java.util.Stack;

public class _1673_FindTheMostCompetitiveSubsequence {
    // time: O(n) space: O(K)
    public int[] mostCompetitive(int[] nums, int k) {
        // 考虑到了反例，但是利用个数来决定是否可以pop是之前没有想到的
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peek() && n - i + stack.size() > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
