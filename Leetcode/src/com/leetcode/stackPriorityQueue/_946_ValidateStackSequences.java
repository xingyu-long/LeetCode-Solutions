/*
 * @Date: 10/11/2019 09:55:12
 * @LastEditTime: 02/26/2021 09:11:12
 * @Description: Stack
 */
package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _946_ValidateStackSequences {
  
    // time:O(n) space:O(n)
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if ((pushed == null || pushed.length == 0)
                && (popped == null || popped.length == 0)) return true;
        if (pushed == null || pushed.length == 0) return false;
        if (popped == null || popped.length == 0) return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0; // for popped operation.
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{}, new int[]{}));
    }
}
