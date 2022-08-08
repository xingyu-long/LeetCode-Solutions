package com.leetcode.stack_priority_queue;

import java.util.Stack;

public class _32_LongestValidParentheses {
    /**
     * 32. Longest Valid Parentheses
     * time:10/25/2019
     * review1:11/4/2019
     * @param s
     * @return
     */
    // time:O(n) space:O(n)
    // 主要是处理以下三种情况：（1）")()()..." 这种的话需要更新leftMost为第一个')'的位置，因为这样相减就刚好等于长度
    // （2）"((())()" 这种情况就不用更新leftMost，因为只需要stack.peek即可
    // （3）"()()()" 刚好都可以抵消这种的话，leftMost没变，只需要做差值之前检查stack是否为空，如果是就用这个值-leftMost计算。
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int leftMost = -1; // 方便计算，如果第一个就是')'这时候会更新leftMost
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    leftMost = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) res = Math.max(res, i - leftMost);
                    else res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}
