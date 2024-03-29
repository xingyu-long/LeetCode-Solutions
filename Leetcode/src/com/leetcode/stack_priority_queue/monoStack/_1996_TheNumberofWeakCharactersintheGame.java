package com.leetcode.stack_priority_queue.monoStack;

import java.util.Arrays;
import java.util.Stack;

public class _1996_TheNumberofWeakCharactersintheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        int n = properties.length;
        Arrays.sort(properties, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && properties[i][1] > stack.peek()) {
                res++;
                stack.pop();
            }
            stack.push(properties[i][1]);
        }
        return res;
    }
}
