package com.leetcode.greedy;

import java.util.Stack;

public class _402_RemoveKDigits {

    /**
     *  402.Remove K Digits
     *  When:2019/7/13
     *  Difficulty: Medium
     *
     *  solution:
     *  Greedy + stack
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < num.length(); i++) {
            while(k > 0 && !stack.isEmpty()
                    && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        // 如果k还大于0，则直接pop就行
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        int res = 0;
        // 找到第一个非0的情况 例如 200100, k = 1那么计算之后是00100 然后res 移动到第三位才对
        while (res < sb.length() && sb.charAt(res) == '0') {
            res++;
        }
        return res == sb.length() ? "0" : sb.substring(res);
    }
}
