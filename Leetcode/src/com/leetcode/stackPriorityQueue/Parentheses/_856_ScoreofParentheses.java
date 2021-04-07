/*
 * @Date: 05/07/2020 14:49:47
 * @LastEditTime: 02/24/2021 09:15:08
 * @Description: Stack, DFS
 */
package com.leetcode.stackPriorityQueue.Parentheses;

import java.util.Stack;

public class _856_ScoreofParentheses {

    public int scoreOfParentheses(String S) {
        // 递归调用做
        return dfs(S, 0, S.length() - 1);
    }

    private int dfs(String s, int i, int j) {
        int res = 0, count = 0;
        for (int k = i; k <= j; k++) {
            count += (s.charAt(k) == '(' ? 1 : -1);
            if (count == 0) {
                if (k - i == 1) {
                    res += 1; // 内部没有其他括号
                } else {
                    res += 2 * dfs(s, i + 1, k - 1); // 内部有括号
                }
                i = k + 1;
            }
        }
        return res;
    }

    // 利用栈
    public int scoreOfParentheses2(String S) {
        // 递归调用做？感觉用栈模拟就可以 只是需要确定如何来表示这个计数
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                stack.push(curr);
                curr = 0;
            } else {
                curr = stack.pop() + Math.max(2 * curr, 1);
            }
        }
        return curr;
    }
}
