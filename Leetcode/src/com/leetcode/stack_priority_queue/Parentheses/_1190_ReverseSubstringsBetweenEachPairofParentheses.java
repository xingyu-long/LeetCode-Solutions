package com.leetcode.stack_priority_queue.Parentheses;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Date: 05/08/2020
 * @Description: Stack,
 **/
public class _1190_ReverseSubstringsBetweenEachPairofParentheses {
    // 递归的写法 O(n^2)
    public String reverseParentheses(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                begin = i; // 走到最近的左括号
            }
            if (s.charAt(i) == ')') {
                end = i; // 最近的右括号
                StringBuilder sb = new StringBuilder(s.substring(begin + 1, end));
                return reverseParentheses(s.substring(0, begin) + sb.reverse().toString() + s.substring(end + 1));
            }
        }
        return s;
    }

    public String reverseParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 遇到右括号弹出
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                List<Character> list = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    list.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // 把'(' 弹出
                }
                for (char revCh : list) {
                    stack.push(revCh);
                }
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
