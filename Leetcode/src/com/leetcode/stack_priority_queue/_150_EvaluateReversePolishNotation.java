package com.leetcode.stack_priority_queue;

import java.util.Stack;

public class _150_EvaluateReversePolishNotation {

    /**
     * 150. Evaluate Reverse Polish Notation
     * When: 2019/06/08
     * Review1: 2019/7/12
     * review2: 2019/9/3
     * Difficulty: Medium
     * <p>
     * solution: 使用stack存储数字，然后遇到符号就pop进行运算。
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (tokens[i].equals("-")) {
                int in1 = stack.pop();
                int in2 = stack.pop();
                stack.push(in2 - in1);
            } else if (tokens[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (tokens[i].equals("/")) {
                int in1 = stack.pop();
                int in2 = stack.pop();
                stack.push(in2 / in1);
            } else {
                // 这里就是数字
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}
