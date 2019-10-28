package com.leetcode.string.Parentheses;

import java.util.Stack;

public class _32_LongestValidParentheses {
    /**
     * 32. Longest Valid Parentheses
     * When:10/26/2019
     * Difficulty: Hard
     * 主要是遇到')' 先看是否为空，如果是 那就只能更新leftMost
     * 如果不是，就pop出来，并且再次看是否为空，是的话就只能比较 i - leftMost，否则是 i-stack.peek();
     * @param s
     * @return
     */
    // 看basketWang的视频
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
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
                    int j = stack.pop();
                    if (stack.isEmpty()) max = Math.max(max, i - leftMost);
                    else max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
