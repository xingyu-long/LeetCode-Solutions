package com.leetcode.stack_priority_queue.Parentheses;

import java.util.Stack;

/**
 * @Date: 05/08/2020, 06/20/2020
 * @Description: Parentheses
 **/
public class _921_MinimumAddtoMakeParenthesesValid {

    // time:O(n) space:O(n)
    public int minAddToMakeValid(String S) {
        // 直接用stack模拟，然后将count（这个是指需要的左括号数目） 加上stack的个数（这个值需要的右括号的数目）
        // ()()(()))))() ))))(((
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    count++;
                } else {
                    stack.pop();
                }
            }
        }
        return count + stack.size();
    }

    // 可以优化为下面这样，思路完全一样，stack记录 '('的个数
    // count 记录没有match上 ')'的个数
    public int minAddToMakeValid2(String S) {
        int stack = 0;
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == '(') {
                stack++;
            } else {
                if (stack == 0) count++;
                else stack--;
            }
        }
        return count + stack;
    }
}
