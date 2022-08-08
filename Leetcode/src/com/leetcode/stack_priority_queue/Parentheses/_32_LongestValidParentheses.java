package com.leetcode.stack_priority_queue.Parentheses;

import java.util.Stack;

/**
 * @Date: 10/26/2019, 05/08/2020
 * @Description: Stack, 记录index
 **/
public class _32_LongestValidParentheses {

    // 主要是遇到')' 先看是否为空，如果是 那就只能更新leftMost
    // 如果不是，就pop出来，并且再次看是否为空，是的话就只能比较 i - leftMost，否则是 i-stack.peek();
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int leftMost = -1; // 当弹出后，栈为空的情况下需要用
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    leftMost = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - leftMost);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    //time:O(n) space:O(1)  two pointer.
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int left = 0, right = 0;
        // first pass: from left to right;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (right > left) {
                left = right = 0; // 因为这样一定产生更长的有效字符（始终差了左括号）
            }
        }

        // second pass: from right to left;
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}
