package com.leetcode.stack_priority_queue;

import java.util.Stack;

/**
 * @Date: 10/25/2020
 * @Description: stack
 **/
public class _682_Baseball_Game {

    public int calPoints(String[] ops) {
        // 栈的操作吧。。。
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(stack.peek() * 2);
            } else if (op.equals("+")) {
                int last = stack.pop();
                int sum = last + stack.peek();
                stack.push(last);
                stack.push(sum);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            // System.out.println(stack.peek());
            res += stack.pop();
        }
        return res;
    }
}
