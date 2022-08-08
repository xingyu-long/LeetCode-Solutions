package com.leetcode.stack_priority_queue.Calculator;

import java.util.Stack;

/**
 * @Date: 07/11/2020
 * @Solution: 利用sign='+'提前占位置，然后遍历到数字或者是运算符才会去计算当前sign。不用stack的话，就需要 +, -的
 * 时候保留到结果中。
 **/
public class _227_BasicCalculatorII {

    // time:O(n) space:O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, num = 0, index = 0;
        int n = s.length();
        while (index < n) {
            char ch = s.charAt(index);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }

            if (index == n - 1 || ch != ' ' && !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                sign = ch;
                num = 0;
            }
            index++;
        }
        for (int i : stack) {
            res += i;
        }
        return res;
    }

    // 利用calculator III的做法。利用currRes和res结合不用栈
    public int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char sign = '+';
        int res = 0, num = 0, index = 0, currRes = 0;
        int n = s.length();
        while (index < n) {
            char ch = s.charAt(index);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }

            if (index == n - 1 || ch != ' ' && !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        currRes += num;
                        break;
                    case '-':
                        currRes -= num;
                        break;
                    case '*':
                        currRes *= num;
                        break;
                    case '/':
                        currRes /= num;
                        break;
                }

                if (ch == '+' || ch == '-' || index == n - 1) {
                    res += currRes;
                    currRes = 0;
                }
                sign = ch;
                num = 0;
            }
            index++;
        }
        return res;
    }
}
