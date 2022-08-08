package com.leetcode.stack_priority_queue.Parentheses;

import java.util.HashMap;
import java.util.Stack;

public class _20_ValidParentheses {
    /**
     * 20. Valid Parentheses
     * When: 2019/8/9
     * review1: 11/4/2019
     * Difficulty: Easy
     * solution: 利用stack，后面pop出来然后比较
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 更加直观
    public boolean isValid2(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false; // 表示只有 右符号加入肯定是false
                if (ch == ')' && stack.peek() != '(' ||
                        ch == ']' && stack.peek() != '[' ||
                        ch == '}' && stack.peek() != '{') return false;
                else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    // more general. 利用hashmap
    public boolean isValid3(String s) {
        if (s == null || s.length() == 0) return true;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                if (stack.isEmpty()) return false;
                if (stack.peek() != map.get(ch)) return false;
                // 满足条件
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
