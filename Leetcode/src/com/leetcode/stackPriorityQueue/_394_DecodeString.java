package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _394_DecodeString {
    /**
     *  394. Decode String
     *  When:2019/7/12
     *  Difficulty: Medium
     *
     * @param s
     * @return
     */
    // time: O(n) space:O(n)
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = count * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                // 这里获得之前的结果，即resStack。
                StringBuilder temp = new StringBuilder(resStack.pop());
                int time = countStack.pop();
                for (int i = 0; i < time; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String input = "3[a]2[bc]";
        System.out.println(decodeString(input));
    }
}