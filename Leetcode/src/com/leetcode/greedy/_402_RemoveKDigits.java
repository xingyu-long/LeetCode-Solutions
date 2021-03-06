package com.leetcode.greedy;

import java.util.Stack;

/**
 * @Date: 2019/7/13, 05/13/2020
 * @Description: Greedy + stack
 **/
public class _402_RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            // 其实有点单调栈的意思在这
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        // 如果k还大于0，则直接pop就行 例如全都一个数字的情况 例如22222222
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String res = sb.reverse().toString();

        int index = 0;
        // 找到第一个非0的情况 例如 200100, k = 1那么计算之后是00100 然后res 移动到第三位才对
        while (index < res.length() && res.charAt(index) == '0') {
            index++;
        }
        return index == res.length() ? "0" : res.substring(index);
    }
}
