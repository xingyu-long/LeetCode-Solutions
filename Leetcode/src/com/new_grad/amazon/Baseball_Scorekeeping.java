package com.new_grad.amazon;

import java.util.Stack;

public class Baseball_Scorekeeping {
    public static int calculateScore(String[] blocks) {
        Stack<Integer> stack = new Stack<>();
        for (String block : blocks) {
            if (block.equals("X")) {
                int curr = stack.peek();
                stack.push(curr * 2);
            } else if (block.equals("+")) {
                int last = stack.peek();
                stack.pop();
                int lastTwo = stack.peek();
                stack.push(last);
                stack.push(last + lastTwo);
            } else if (block.equals("Z")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(block));
            }
        }
        int res = 0;
        for (int num : stack) {
            res += num;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculateScore(new String[]{"10", "20", "X", "+"}));
        System.out.println(calculateScore(new String[]{"10", "20", "Z", "30", "+"}));
    }
}
