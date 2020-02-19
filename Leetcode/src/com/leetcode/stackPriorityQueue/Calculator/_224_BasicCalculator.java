package com.leetcode.stackPriorityQueue.Calculator;

import java.util.Stack;

public class _224_BasicCalculator {

    /**
     * 224. Basic Calculator
     * When: 2019/06/15
     * review1: 2019/9/4
     *
     * <p>
     * solution: 如下所示
     *
     * @param s
     * @return
     */
    // time:O(n) space:O(n)
    public int calculate(String s) {
        // 这种相当每次先计算之前的情况，遇到左括号就先把之前的结果保留起来，遇到右括号就开始算。
        // 使用一个stack来实现，之前那种方法对于后缀表达式很有用，但是这种情况还是使用如下的方式
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        int number = 0;
        int sign = 1; //用来标记正负
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
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
                res *= stack.pop();  // 表示在括号之前的符号+ -
                res += stack.pop(); // 括号之前的数字
            }
        }
        if (number != 0) res += sign * number;
        return res;
    }


    // 遇到下一个操作符才开始计算
    // 利用calculator III里面的部分解法实现，基本思路就是走到那里就开始存前面的到结果集中
    // time:O(n^2) space:O(n)
    public int calculate2(String s) {
        if (s == null || s.length() == 0) return 0;
        char sign = '+';
        int res = 0, currRes = 0, num = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (s.charAt(i) == '(') {
                // 这里用的很妙，控制i来找到')' 反正下一轮也是i++的位置了
                int j = i, count = 0;
                for (; i < n; i++) {
                    if (s.charAt(i) == '(') count++;
                    if (s.charAt(i) == ')') count--;
                    if (count == 0) break;
                }
                num = calculate2(s.substring(j + 1, i));
            }

            if (ch == '+' || ch == '-' || i == n - 1) {
                switch(sign) {
                    case '+' : currRes += num; break;
                    case '-' : currRes -= num; break;
                }
                if (ch == '+' || ch == '-' || i == n - 1) {
                    res += currRes;
                    currRes = 0;
                }

                sign = ch;
                num = 0;
            }
        }
        return res;
    }
}
