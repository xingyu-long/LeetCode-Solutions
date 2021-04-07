/*
 * @Date: 04/25/2020 10:43:56
 * @LastEditTime: 02/19/2021 08:58:55
 * @Description: Stack
 */
package com.leetcode.stackPriorityQueue.Parentheses;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class _1249_MinimumRemovetoMakeValidParentheses {

    // 主要思想还是利用括号匹配来进行，记录mismatched的位置，不加入就可以了
    // 没有想到
    public String minRemoveToMakeValid(String s) {
        // 依然是使用stack来 决定左右括号是否匹配
        Stack<Integer> stack = new Stack<>();
        // 记录mismatch的地方
        Set<Integer> mismatch = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    mismatch.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            mismatch.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!mismatch.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
