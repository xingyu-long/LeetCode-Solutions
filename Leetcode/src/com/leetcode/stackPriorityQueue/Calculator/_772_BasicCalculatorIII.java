package com.leetcode.stackPriorityQueue.Calculator;

public class _772_BasicCalculatorIII {

    public int calculate(String s) {
        int n = s.length(), num = 0, currRes = 0, res = 0;
        char sign = '+';
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '(') {
                int j = i, count = 0;
                for (; i < n; i++) {
                    if (s.charAt(i) == '(') count++;
                    if (s.charAt(i) == ')') count--;
                    if (count == 0) break;
                }
                num = calculate(s.substring(j + 1, i));
            }
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == n - 1) {
                switch (sign) {
                    case '+' : currRes += num; break;
                    case '-' : currRes -= num; break;
                    case '*' : currRes *= num; break;
                    case '/' : currRes /= num; break;
                }

                // 这个curr其实替代了stack的作用
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

    public static void main(String[] args) {
        _772_BasicCalculatorIII calculatorIII = new _772_BasicCalculatorIII();
        String s = "1+2*(2 -100)-5*6";
        System.out.print(calculatorIII.calculate(s));
    }
}
