package com.pramp;

import java.util.Stack;

public class _Oct_14_BracketMatch {
    /**
     * input:  text = “(()”
     * output: 1
     * <p>
     * input:  text = “(())”
     * output: 0
     * <p>
     * input:  text = “())(”
     * output: 2
     *
     * @param text
     * @return
     */
    // time:O(n) space:O(n) stack.
    public static int bracketMatch(String text) {
        // your code goes here
        // eg: " ( ( ) " stack: "(( "
        if (text == null || text.length() == 0) return 0;
        Stack<Character> stack = new Stack<>();
        for (char ch : text.toCharArray()) {
            if (ch == '(') stack.push(ch);
            else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else { // 其他没有匹配上的 ')'
                stack.push(ch);
            }
        }
        return stack.size();
    }

    // time:O(N) space:O(1)
    public static int bracketMatch2(String text) {
        if (text == null || text.length() == 0) return 0;
        int diff = 0;
        int res = 0;
        for (char ch : text.toCharArray()) {
            if (ch == '(') diff++;
            else if (ch == ')') diff--;

            if (diff < 0) {
                res++;
                diff = 0; // 相当于添加了一个我们需要补充的。
            }
        }
        // diff为正就说明一直是'('
        return diff + res;
    }
}