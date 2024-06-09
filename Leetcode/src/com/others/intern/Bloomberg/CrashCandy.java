package com.intern.Bloomberg;

import java.util.Stack;

public class CrashCandy {

    public static String removeDuplicates(String s) {
        if (s == null || s.length() == 0) return null;
        // 需要大于等于3的连续都会被移除
        int k = 3;
        // 利用stack
        Stack<Character> chStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!chStack.isEmpty() && chStack.peek() == ch) {
                chStack.push(ch);
                countStack.push(1 + countStack.pop());
            } else {
                chStack.push(ch);
                countStack.push(1);
            }

            if (i + 1 < s.length() && countStack.peek() >= 3 && s.charAt(i + 1) != ch) {
                int size = countStack.pop();
                for (int j = 0; j < size; j++) {
                    chStack.pop();
                }
            }
        }
        // 最后check一遍
        if (countStack.peek() >= 3) {
            int size = countStack.pop();
            for (int j = 0; j < size; j++) {
                chStack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!chStack.isEmpty()) {
            sb.append(chStack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("acbbbccddd"));
    }
}
