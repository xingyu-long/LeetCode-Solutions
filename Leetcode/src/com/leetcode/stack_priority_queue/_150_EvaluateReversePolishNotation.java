package com.leetcode.stack_priority_queue;

import java.util.Stack;

public class _150_EvaluateReversePolishNotation {

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
