package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _227_BasicCalculatorII {
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        char sign = '+';// 一开始就有一个'+'
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            // 这里也是if是因为需要i= i+1的情况
            // 相当于先都把这些加入 然后再判断
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' '
                    || i == s.length() - 1) {
                if (sign == '+') stack.push(num);
                if (sign == '-') stack.push(-num);
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        for (int i : stack) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        calculate("3+2*2");
    }
}
