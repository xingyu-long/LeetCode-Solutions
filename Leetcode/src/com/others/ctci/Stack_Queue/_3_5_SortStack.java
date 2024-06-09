package com.ctci.Stack_Queue;

import java.util.Stack;

public class _3_5_SortStack {

    public void sort(Stack<Integer> stack) {
        Stack<Integer> res = new Stack<>();
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            while (!res.isEmpty() && res.peek() > temp) {
                stack.push(res.pop());
            }
            res.push(temp);
        }
    }
}
