package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _224_BasicCalculator {

    /**
     *  224. Basic Calculator
        When: 2019/06/15

        solution: 如下所示
     * @param s
     * @return
     */
    public int calculate(String s) {
        // 使用一个stack来实现，之前那种方法对于后缀表达式很有用，但是这种情况还是使用如下的方式
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        int number = 0;
        int sign = 1; //用来标记正负
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int)(c - '0');
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // 相当于计算每一个括号内的结果 后面就依次向外弹出
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }
        if (number != 0) res += sign * number;
        return res;
    }
}
